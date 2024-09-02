package com.taxi.booking.booking.service;

import com.taxi.booking.booking.converter.OrderViewConverter;
import com.taxi.booking.booking.model.Action;
import com.taxi.booking.booking.model.OrderInitView;
import com.taxi.booking.booking.model.OrderView;
import com.taxi.booking.cars.model.TaxiStatus;
import com.taxi.booking.common.db.CarRepository;
import com.taxi.booking.common.db.OrderRepository;
import com.taxi.booking.common.db.entity.OrderEntity;
import com.taxi.booking.common.db.entity.OrderStatus;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

  private final OrderActionValidator actionValidator;
  private final OrderRepository orderRepository;
  private final CarRepository carRepository;

  public OrderService(
      OrderRepository orderRepository,
      OrderActionValidator actionValidator,
      CarRepository carRepository) {
    this.orderRepository = orderRepository;
    this.actionValidator = actionValidator;
    this.carRepository = carRepository;
  }


  public UUID createOrder(OrderInitView orderInit) {
    OrderEntity order = new OrderEntity();

    order.setId(UUID.randomUUID());
    order.setFromLongitude(orderInit.getFrom().getLon());
    order.setFromLatitude(orderInit.getFrom().getLat());
    order.setToLongitude(orderInit.getTo().getLon());
    order.setToLatitude(orderInit.getTo().getLat());
    order.setStatus(OrderStatus.INITIATED);

    return orderRepository.save(order).getId();
  }

  public List<OrderView> listOrders(List<OrderStatus> filterStatuses) {
    return (CollectionUtils.isEmpty(filterStatuses)
        ? orderRepository.findAll()
        : orderRepository.findAllByStatusIn(filterStatuses)).stream()
        .map(OrderViewConverter::convert)
        .sorted(Comparator.comparing(OrderView::getCreationTime).reversed())
        .toList();
  }

  public void updateOrder(UUID orderId, Action action, UUID carId) {
    orderRepository.findById(orderId)
        .ifPresent(order -> {
          actionValidator.validateOrderAction(order.getStatus(), action);
          switch (action) {
            case TAKE -> takeOrder(order, carId);
            case START -> startOrder(order);
            case CANCEL -> cancelOrder(order);
            case COMPLETE -> completeOrder(order);
          }
        });
  }

  private void takeOrder(OrderEntity order, UUID carId) {
    carRepository.findById(carId).ifPresent(car -> {
      order.setCarId(car.getId());
      order.setStatus(OrderStatus.TAKEN);
      car.setStatus(TaxiStatus.BOOKED);
      orderRepository.save(order);
      carRepository.save(car);
    });
  }

  private void cancelOrder(OrderEntity order) {
    order.setStatus(OrderStatus.INITIATED);
    if (order.getCarId() != null) {
      carRepository.findById(order.getCarId()).ifPresent(car -> {
        order.setCarId(null);
        car.setStatus(TaxiStatus.AVAILABLE);
        carRepository.save(car);
      });
    }
    orderRepository.save(order);
  }

  private void startOrder(OrderEntity order) {
    order.setStatus(OrderStatus.IN_PROGRESS);
    orderRepository.save(order);
  }

  private void completeOrder(OrderEntity order) {
    order.setStatus(OrderStatus.COMPETED);
    orderRepository.save(order);
    carRepository.findById(order.getCarId()).ifPresent(car -> {
      car.setStatus(TaxiStatus.AVAILABLE);
      carRepository.save(car);
    });
  }
}
