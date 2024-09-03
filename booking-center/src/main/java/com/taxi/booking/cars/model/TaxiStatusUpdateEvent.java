package com.taxi.booking.cars.model;

import java.util.UUID;

public class TaxiStatusUpdateEvent {

  private UUID carId;
  private TaxiStatus status;

  public UUID getCarId() {
    return carId;
  }

  public void setCarId(UUID carId) {
    this.carId = carId;
  }

  public TaxiStatus getStatus() {
    return status;
  }

  public void setStatus(TaxiStatus status) {
    this.status = status;
  }
}
