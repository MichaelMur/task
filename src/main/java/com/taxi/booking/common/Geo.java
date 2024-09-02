package com.taxi.booking.common;

public class Geo {

  public Geo(double latitude, double longitude) {
    this.lat = latitude;
    this.lon = longitude;
  }

  private double lat;
  private double lon;

  public double getLat() {
    return lat;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public double getLon() {
    return lon;
  }

  public void setLon(double lon) {
    this.lon = lon;
  }
}
