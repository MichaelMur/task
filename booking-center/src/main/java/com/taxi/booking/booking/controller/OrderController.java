package com.taxi.booking.booking.controller;

import com.taxi.booking.booking.model.Action;
import com.taxi.booking.booking.model.OrderInitView;
import com.taxi.booking.booking.model.OrderView;
import com.taxi.booking.booking.service.OrderService;
import com.taxi.booking.common.db.entity.OrderStatus;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

  private final Logger log = LoggerFactory.getLogger(OrderController.class);

  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping
  public UUID createOrder(@RequestBody OrderInitView orderInit) {
    UUID orderId = orderService.createOrder(orderInit);
    log.info("Order {} created", orderId);
    return orderId;
  }

  @GetMapping
  public List<OrderView> ordersList(
      @RequestParam(required = false) List<OrderStatus> filterStatuses) {
    return orderService.listOrders(filterStatuses);
  }

  @PatchMapping("/{orderId}/{action}")
  public void updateOrder(
      @PathVariable UUID orderId,
      @PathVariable Action action,
      @RequestParam UUID carId) {
    orderService.updateOrder(orderId, action, carId);
    log.info("Order {} {} by car {}", orderId, action, carId);
  }
}
