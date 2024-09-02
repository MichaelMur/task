package com.taxi.booking.common.db.entity;

import com.taxi.booking.cars.model.TaxiStatus;
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
@Table(name = "cars")
public class CarEntity {

  @Id
  private UUID id;
  @Column
  @CreationTimestamp
  private LocalDateTime registrationDate;
  @Column
  @UpdateTimestamp
  private LocalDateTime lastUpdateDate;
  @Column
  @Enumerated(EnumType.STRING)
  private TaxiStatus status;
  @Column
  private double longitude;
  @Column
  private double latitude;
  @Column
  private String driverName;
  @Column
  private String driverId;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LocalDateTime getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(LocalDateTime registrationDate) {
    this.registrationDate = registrationDate;
  }

  public LocalDateTime getLastUpdateDate() {
    return lastUpdateDate;
  }

  public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
    this.lastUpdateDate = lastUpdateDate;
  }

  public TaxiStatus getStatus() {
    return status;
  }

  public void setStatus(TaxiStatus status) {
    this.status = status;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
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
