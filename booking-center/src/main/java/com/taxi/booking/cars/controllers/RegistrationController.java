package com.taxi.booking.cars.controllers;

import com.taxi.booking.cars.model.TaxiInfo;
import com.taxi.booking.cars.model.TaxiStatusUpdateEvent;
import com.taxi.booking.cars.service.CarManagementService;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taxi")
public class RegistrationController {

  private final Logger log = LoggerFactory.getLogger(RegistrationController.class);

  private final CarManagementService carManagementService;

  public RegistrationController(CarManagementService carManagementService) {
    this.carManagementService = carManagementService;
  }

  @PostMapping("/registration")
  public UUID registerCar(@RequestBody TaxiInfo info) {
    UUID carId = carManagementService.registerCar(info);
    log.info("Car {} registered", carId);
    return carId;
  }

  @PostMapping("/stop")
  public void disableCar(@RequestBody UUID id) {
    carManagementService.removeCar(id);
    log.info("Car {} disabled", id);
  }

  @PostMapping("/state")
  public void registerCar(@RequestBody TaxiStatusUpdateEvent event) {
    carManagementService.updateStatus(event.getCarId(), event.getStatus());
  }
}
