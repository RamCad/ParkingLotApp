package com.poc.parkinglot.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingTicket {
  private Integer ticketNum;
  private Vehicle vehicle;
  private ParkingSpot spot;
  private LocalDateTime entryTime;
}
