package com.taxi.booking.cars.converter;

import com.taxi.booking.cars.model.CarStatusInfo;
import com.taxi.booking.common.Geo;
import com.taxi.booking.common.db.entity.CarEntity;

public class CarStatusInfoConverter {

  private CarStatusInfoConverter() {
  }

  public static CarStatusInfo convert(CarEntity source) {
    CarStatusInfo target = new CarStatusInfo();
    target.setId(source.getId());
    target.setStatus(source.getStatus());
    target.setRegistered(source.getRegistrationDate());
    target.setLastUpdate(source.getLastUpdateDate());
    target.setGeo(new Geo(source.getLatitude(), source.getLongitude()));
    target.setDriverName(source.getDriverName());
    target.setDriverId(source.getDriverId());
    return target;
  }
}
