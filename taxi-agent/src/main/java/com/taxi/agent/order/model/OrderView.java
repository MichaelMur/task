package com.taxi.agent.order.model;

import com.taxi.agent.telemetry.model.Geo;
import java.util.UUID;

public class OrderView {

  private UUID id;
  private Geo from;
  private Geo to;

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

}
