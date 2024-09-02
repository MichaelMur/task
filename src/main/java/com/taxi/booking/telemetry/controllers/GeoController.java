package com.taxi.booking.telemetry.controllers;

import com.taxi.booking.common.Geo;
import com.taxi.booking.telemetry.service.CarGeoService;
import java.util.UUID;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class GeoController {

  private final CarGeoService carGeoService;

  public GeoController(CarGeoService carGeoService) {
    this.carGeoService = carGeoService;
  }

  @PatchMapping("/{id}/geo")
  public void updateGeo(
      @PathVariable UUID id,
      @RequestBody Geo geo
  ) {
    carGeoService.updateGeo(id, geo);
  }
}
