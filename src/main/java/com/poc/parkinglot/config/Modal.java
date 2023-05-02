package com.poc.parkinglot.config;

import com.poc.parkinglot.model.SpotSize;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Modal {
  private SpotSize size;
  private Long startInterval;
  private Long endInterval;
  private Long fee;
  private boolean isFlatRate;
}
