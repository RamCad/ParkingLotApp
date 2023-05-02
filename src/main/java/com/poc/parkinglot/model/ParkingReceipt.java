package com.poc.parkinglot.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingReceipt {
  private String receiptNum;
  private LocalDateTime entryTime;
  private LocalDateTime exitTime;
  private Long fees;
}
