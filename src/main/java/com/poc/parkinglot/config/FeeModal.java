package com.poc.parkinglot.config;

import com.poc.parkinglot.model.SpotSize;
import java.util.HashMap;
import java.util.Map;

public class FeeModal {

  public static Map<SpotSize, Integer> getMallModal() {
    Map<SpotSize, Integer> spots = new HashMap<>();
    spots.put(SpotSize.S, 10);
    spots.put(SpotSize.M, 20);
    spots.put(SpotSize.L, 50);
    return spots;
  }


}
