package com.taxi.booking.common.db.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "orders")
public class OrderEntity {

  @Id
  private UUID id;

  @Column
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @Column
  private double fromLongitude;
  @Column
  private double fromLatitude;

  @Column
  private double toLongitude;
  @Column
  private double toLatitude;

  @Column
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Column
  private UUID carId;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public double getFromLongitude() {
    return fromLongitude;
  }

  public void setFromLongitude(double fromLongitude) {
    this.fromLongitude = fromLongitude;
  }

  public double getFromLatitude() {
    return fromLatitude;
  }

  public void setFromLatitude(double fromLatitude) {
    this.fromLatitude = fromLatitude;
  }

  public double getToLongitude() {
    return toLongitude;
  }

  public void setToLongitude(double toLongitude) {
    this.toLongitude = toLongitude;
  }

  public double getToLatitude() {
    return toLatitude;
  }

  public void setToLatitude(double toLatitude) {
    this.toLatitude = toLatitude;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public UUID getCarId() {
    return carId;
  }

  public void setCarId(UUID carId) {
    this.carId = carId;
  }
}
