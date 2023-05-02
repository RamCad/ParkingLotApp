package com.poc.parkinglot.controller;

import com.poc.parkinglot.model.Vehicle;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingLotController {

  @PostMapping("/parkingLot")
  public void createParkingLot(@RequestBody Map<Object, Object> parkingLot){
    // RequestBody
    // smallVehicles - MotorCycle/Scooter
    // mediumVehicles - Car/SUV
    // largeVehicles - Bus/Truck
    // FeeModal
  }

  @PostMapping
  public void parkVehicle(@RequestBody Vehicle vehicle) {
    // RequestBody
  }
}
