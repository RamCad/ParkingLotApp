package com.poc.parkinglot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingSpot {
  private Integer id;
  private boolean isOccupied;
  private SpotSize size;
  private Vehicle vehicle;
}
