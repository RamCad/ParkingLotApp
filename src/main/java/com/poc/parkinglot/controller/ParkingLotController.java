//package com.poc.parkinglot.controller;
//
//import com.poc.parkinglot.model.Vehicle;
//import java.util.Map;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/parkingLot")
//public class ParkingLotController {
//
//  @PostMapping
//  public void createParkingLot(@RequestBody Map<Object, Object> parkingLot){
//    // RequestBody
//    // smallVehicles - MotorCycle/Scooter
//    // mediumVehicles - Car/SUV
//    // largeVehicles - Bus/Truck
//    // FeeModal
//  }
//
//  @PostMapping("/park")
//  public void parkVehicle(@RequestBody Vehicle vehicle) {
//
//  }
//
//  @PostMapping("/unPark/{ticketNum}")
//  public void unParkVehicle(@RequestBody Vehicle vehicle, @PathVariable String ticketNum) {
//
//  }
//}
