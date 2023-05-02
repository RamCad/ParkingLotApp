package com.poc.parkinglot.model;

public enum SpotSize {
  S, M, L;

  public Boolean canFit(Vehicle vehicle) {
    switch (this) {
      case S:
        return vehicle instanceof Motorcycle || vehicle instanceof Scooter;
      case M:
        return vehicle instanceof Car || vehicle instanceof SUV;
      case L:
        return vehicle instanceof Bus || vehicle instanceof Truck;
      default:
        return false;
    }
  }
}
