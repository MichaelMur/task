package com.taxi.agent.common;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.stereotype.Service;

@Service
public class StaticInfoHolder {

  private final AtomicReference<UUID> carId = new AtomicReference<>();
  private final AtomicReference<String> driverName = new AtomicReference<>();
  private final AtomicReference<String> driverId = new AtomicReference<>();

  public void setCarId(UUID carId) {
    this.carId.set(carId);
  }

  public UUID getCarId() {
    return carId.get();
  }

  public void setDriverName(String driverName) {
    this.driverName.set(driverName);
  }

  public String getDriverName() {
    return driverName.get();
  }

  public void setDriverId(String driverId) {
    this.driverId.set(driverId);
  }

  public String getDriverId() {
    return driverId.get();
  }
}
