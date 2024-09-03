package com.taxi.booking.booking.model;

import com.taxi.booking.common.Geo;

public class OrderInitView {

  private Geo from;
  private Geo to;

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
}
