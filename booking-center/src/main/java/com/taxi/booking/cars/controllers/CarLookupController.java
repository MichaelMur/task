package com.taxi.booking.cars.controllers;

import com.taxi.booking.cars.model.CarStatusInfo;
import com.taxi.booking.cars.service.CarManagementService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taxi")
public class CarLookupController {

  private final CarManagementService carManagementService;

  public CarLookupController(CarManagementService carManagementService) {
    this.carManagementService = carManagementService;
  }

  @GetMapping()
  public List<CarStatusInfo> listAllCars() {
    return carManagementService.listCars();
  }
}
