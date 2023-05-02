package com.poc.parkinglot.exception;

public class InvalidParkingTicketException extends RuntimeException {
  public InvalidParkingTicketException(String errorMessage) {
    super(errorMessage);
  }
}
