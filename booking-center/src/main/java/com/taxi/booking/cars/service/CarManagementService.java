package com.taxi.booking.cars.service;

import com.taxi.booking.cars.converter.CarStatusInfoConverter;
import com.taxi.booking.cars.model.CarStatusInfo;
import com.taxi.booking.cars.model.TaxiInfo;
import com.taxi.booking.cars.model.TaxiStatus;
import com.taxi.booking.common.db.CarRepository;
import com.taxi.booking.common.db.entity.CarEntity;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarManagementService {

  private final CarRepository carRepository;

  public CarManagementService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }


  public UUID registerCar(TaxiInfo info) {
    CarEntity newCar = new CarEntity();
    newCar.setId(UUID.randomUUID());
    newCar.setStatus(TaxiStatus.AVAILABLE);
    newCar.setLongitude(info.getLon());
    newCar.setLatitude(info.getLat());
    newCar.setDriverName(info.getDriverName());
    newCar.setDriverId(info.getDriverId());
    return carRepository.save(newCar).getId();
  }

  public void removeCar(UUID id) {
    carRepository.deleteById(id);
  }


  public List<CarStatusInfo> listCars() {
    return carRepository.findAll().stream()
        .map(CarStatusInfoConverter::convert)
        .sorted(Comparator.comparing(CarStatusInfo::getRegistered).reversed())
        .toList();
  }

  public void updateStatus(UUID carId, TaxiStatus status) {
    carRepository.findById(carId).ifPresent(car -> {
      car.setStatus(status);
      carRepository.save(car);
    });
  }
}
