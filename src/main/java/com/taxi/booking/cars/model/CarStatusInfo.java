package com.taxi.booking.cars.model;

import com.taxi.booking.common.Geo;
import java.time.LocalDateTime;
import java.util.UUID;

public class CarStatusInfo {

  private UUID id;
  private LocalDateTime registered;
  private LocalDateTime lastUpdate;
  private TaxiStatus status;
  private Geo geo;
  private String driverName;
  private String driverId;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LocalDateTime getRegistered() {
    return registered;
  }

  public void setRegistered(LocalDateTime registered) {
    this.registered = registered;
  }

  public LocalDateTime getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(LocalDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public TaxiStatus getStatus() {
    return status;
  }

  public void setStatus(TaxiStatus status) {
    this.status = status;
  }

  public Geo getGeo() {
    return geo;
  }

  public void setGeo(Geo geo) {
    this.geo = geo;
  }

  public String getDriverName() {
    return driverName;
  }

  public void setDriverName(String driverName) {
    this.driverName = driverName;
  }

  public String getDriverId() {
    return driverId;
  }

  public void setDriverId(String driverId) {
    this.driverId = driverId;
  }
}
