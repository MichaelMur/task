package com.taxi.booking.booking.model;

import com.taxi.booking.common.Geo;
import com.taxi.booking.common.db.entity.OrderStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public class OrderView {

  private UUID id;
  private Geo from;
  private Geo to;

  private OrderStatus status;
  private LocalDateTime creationTime;
  private LocalDateTime lastUpdate;

  private UUID carAssigned;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Geo getFrom() {
    return from;
  }

  public void setFrom(Geo from) {
    this.from = from;
  }

  public Geo getTo() {
    return to;
  }

  public void setTo(Geo to) {
    this.to = to;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(LocalDateTime creationTime) {
    this.creationTime = creationTime;
  }

  public LocalDateTime getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(LocalDateTime lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

  public UUID getCarAssigned() {
    return carAssigned;
  }

  public void setCarAssigned(UUID carAssigned) {
    this.carAssigned = carAssigned;
  }
}
