package com.poc.parkinglot.config;

import com.poc.parkinglot.model.SpotSize;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Assumptions as per prepared data
 * If startIdx, lastIdx -1 - per day rate
 */
public class FeeModal {

  private static final String PREFIX = "src/main/resources/";
  private static final String COMMA_DELIMITER = ",";

  public static Map<SpotSize, List<Modal>> getFeeModalData(String feeModal) {
    List<Modal> feeRecords = readModalData(feeModal);

    return feeRecords.stream()
        .collect(Collectors.groupingBy(Modal::getSize));
  }

  private static List<Modal> readModalData(String feeModal) {
    List<Modal> records = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(PREFIX + feeModal + ".csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(COMMA_DELIMITER);
        Modal modal = Modal.builder()
            .startInterval(Long.parseLong(values[0]))
            .endInterval(Long.parseLong(values[1]))
            .fee(Long.parseLong(values[2]))
            .isFlatRate(Boolean.parseBoolean(values[3]))
            .build();
        records.add(modal);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace(); // todo - logging
    } catch (IOException e) {
      e.printStackTrace();
    }
    return records;
  }


}
