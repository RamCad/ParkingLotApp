package com.poc.parkinglot.TestService;

import com.poc.parkinglot.model.Motorcycle;
import com.poc.parkinglot.model.ParkingReceipt;
import com.poc.parkinglot.model.ParkingTicket;
import com.poc.parkinglot.model.Scooter;
import com.poc.parkinglot.model.Vehicle;
import com.poc.parkinglot.service.ParkingLotService;

public class ParkingLotServiceTest {

  public static void main(String[] args) {

    // scan input for below fields

    Integer smallVehicles = 2;
    Integer mediumVehicles = 2;
    Integer largeVehicles = 0;
    String feeModal = "Mall";

    ParkingLotService parkingLotService = new ParkingLotService(smallVehicles, mediumVehicles, largeVehicles, feeModal);

    Motorcycle motorcycle = new Motorcycle("M1");

    ParkingTicket parkingTicket = parkingLotService.parkVehicle(motorcycle);

    System.out.println(parkingTicket);

    Scooter scooter = new Scooter("S1");

    ParkingTicket parkingTicket2 = parkingLotService.parkVehicle(scooter);

    System.out.println(parkingTicket2);

//    Scooter scooter1 = new Scooter("S2");
//
//    ParkingTicket parkingTicket1 = parkingLotService.parkVehicle(scooter1);
//
//    System.out.println(parkingTicket1);

    ParkingReceipt receipt = parkingLotService.unParkVehicle(scooter,
        parkingTicket2.getTicketNum());

    System.out.println(receipt);
  }
}
