package com.taxi.booking.telemetry.service;

import com.taxi.booking.common.Geo;
import com.taxi.booking.common.db.CarRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarGeoService {

  private final CarRepository carRepository;

  public CarGeoService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }


  public void updateGeo(UUID id, Geo geo) {
    carRepository.findById(id).ifPresent(car -> {
      car.setLatitude(geo.getLat());
      car.setLongitude(geo.getLon());
      carRepository.save(car);
    });
  }
}
