package com.poc.parkinglot.service;

import com.poc.parkinglot.config.FeeModal;
import com.poc.parkinglot.config.Modal;
import com.poc.parkinglot.exception.InvalidParkingTicketException;
import com.poc.parkinglot.exception.SpotNotAvailableException;
import com.poc.parkinglot.model.ParkingReceipt;
import com.poc.parkinglot.model.ParkingSpot;
import com.poc.parkinglot.model.ParkingTicket;
import com.poc.parkinglot.model.SpotSize;
import com.poc.parkinglot.model.Vehicle;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class ParkingLotService {

  private Integer smallVehicles;
  private Integer mediumVehicles;
  private Integer largeVehicles;
  private String feeModal;
  private List<ParkingSpot> availableSpots;
  private Map<Integer, ParkingTicket> parkingTickets;
  private Map<Integer, ParkingReceipt> parkingReceipts;

  public ParkingLotService(Integer smallVehicles, Integer mediumVehicles,
      Integer largeVehicles, String feeModal) {
    this.availableSpots = new ArrayList<>();
    this.parkingTickets = new HashMap<>();
    this.parkingReceipts = new HashMap<>();
    this.smallVehicles = smallVehicles;
    this.mediumVehicles = mediumVehicles;
    this.largeVehicles = largeVehicles;
    this.feeModal = feeModal; // can be converted to Enum
    init();
  }

  private void init() {
    IntStream.range(1, smallVehicles + 1)
        .forEach(idx -> availableSpots.add(ParkingSpot.builder()
            .id(idx)
            .size(SpotSize.S)
            .build()));
    IntStream.range(1, mediumVehicles + 1)
        .forEach(idx -> availableSpots.add(ParkingSpot.builder()
            .id(idx)
            .size(SpotSize.M)
            .build()));
    IntStream.range(1, largeVehicles + 1)
        .forEach(idx -> availableSpots.add(ParkingSpot.builder()
            .id(idx)
            .size(SpotSize.L)
            .build()));
  }

  public ParkingTicket parkVehicle(Vehicle vehicle) {
    ParkingSpot spotAvailable = findSpot(availableSpots, vehicle);
    if (Objects.isNull(spotAvailable)) {
      throw new SpotNotAvailableException(
          "No spots available for vehicle: + " + vehicle.getNumber());
    }
    spotAvailable.setVehicle(vehicle);
    spotAvailable.setOccupied(true);
    int ticketId = parkingTickets.size();
    ParkingTicket parkingTicket = ParkingTicket.builder()
        .ticketNum(++ticketId)
        .vehicle(vehicle)
        .spot(spotAvailable)
        .entryTime(LocalDateTime.now())
        .build();

    parkingTickets.put(ticketId, parkingTicket);
    return parkingTicket;
  }

  public ParkingReceipt unParkVehicle(Vehicle vehicle, Integer ticketNum) {
    ParkingTicket parkingTicket = parkingTickets.get(ticketNum);
    if (Objects.isNull(parkingTicket)) {
      throw new InvalidParkingTicketException("Parking Ticket is not valid: " + ticketNum);
    }
    LocalDateTime exitTime = LocalDateTime.now();

    // calculate fees
    Long fees = calculateFees(parkingTicket.getEntryTime(), exitTime, feeModal,
        parkingTicket.getSpot().getSize());
    Integer receiptNum = parkingReceipts.size() + 1;
    ParkingReceipt receipt = ParkingReceipt.builder()
        .receiptNum("R-" + receiptNum)
        .entryTime(parkingTicket.getEntryTime())
        .exitTime(exitTime)
        .fees(fees)
        .build();

    // refactor
    parkingTicket.getSpot().setVehicle(null);
    parkingTicket.getSpot().setOccupied(false);
    parkingTickets.remove(ticketNum);
    parkingReceipts.put(receiptNum, receipt);
    return receipt;
  }

  private ParkingSpot findSpot(List<ParkingSpot> availableSpots, Vehicle vehicle) {
    return availableSpots.stream()
        .filter(
            parkingSpot -> !parkingSpot.isOccupied() && parkingSpot.getSize().canFit(vehicle))
        .findFirst()
        .orElseGet(() -> null);
  }

  //
  private Long calculateFees(LocalDateTime entryTime, LocalDateTime exitTime, String feeModal,
      SpotSize spotSize) {
    Duration timeDiff = Duration.between(entryTime, exitTime);
    Map<SpotSize, List<Modal>> feeModalData = FeeModal.getFeeModalData(feeModal); // make it generic using feeModal
    List<Modal> modals = feeModalData.get(spotSize);
    Modal modal;
    if(modals.size() == 1) {
      modal = modals.get(0);
    } else {
      modal = modals.stream()
          .filter(m -> findRange(m.getStartInterval(), m.getEndInterval(), timeDiff.toHours()))
          .findFirst()
          .orElseGet(() -> modals.get(modals.size() - 1));
    }
    return getFees(modal, timeDiff);
  }

  private Long getFees(Modal modal, Duration timeDiff) {
    if (modal.isFlatRate()) {
      return modal.getFee();
    } else if(modal.getStartInterval() == -1 && modal.getEndInterval() == -1) {
      return timeDiff.toDays() * modal.getFee();
    }
    return timeDiff.toHours() == 0 ? modal.getFee() : timeDiff.toHours() * modal.getFee();
  }

  private boolean findRange(Long startIdx, Long endIdx, Long number) {
    return (startIdx <= number && number < endIdx);
  }
}
