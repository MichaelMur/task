package com.taxi.booking.booking.converter;

import com.taxi.booking.booking.model.OrderView;
import com.taxi.booking.common.Geo;
import com.taxi.booking.common.db.entity.OrderEntity;

public class OrderViewConverter {

  private OrderViewConverter() {
  }

  public static OrderView convert(OrderEntity order) {
    OrderView orderView = new OrderView();
    orderView.setId(order.getId());
    orderView.setFrom(new Geo(order.getFromLatitude(), order.getFromLongitude()));
    orderView.setTo(new Geo(order.getToLatitude(), order.getToLongitude()));
    orderView.setStatus(order.getStatus());
    orderView.setCreationTime(order.getCreatedAt());
    orderView.setLastUpdate(order.getUpdatedAt());
    orderView.setCarAssigned(order.getCarId());
    return orderView;
  }
}
