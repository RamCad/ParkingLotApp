package com.poc.parkinglot.exception;

public class SpotNotAvailableException extends RuntimeException {
  public SpotNotAvailableException(String errorMessage) {
    super(errorMessage);
  }
}
