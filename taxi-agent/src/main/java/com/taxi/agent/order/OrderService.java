package com.taxi.agent.order;

import static java.util.Arrays.stream;

import com.taxi.agent.common.StaticInfoHolder;
import com.taxi.agent.order.model.OrderView;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

  private final RestTemplate restTemplate;
  private final StaticInfoHolder staticInfoHolder;

  public OrderService(RestTemplate restTemplate, StaticInfoHolder staticInfoHolder) {
    this.restTemplate = restTemplate;
    this.staticInfoHolder = staticInfoHolder;
  }

  @Scheduled(fixedRate = 2000)
  public void findOrder() {
    if (staticInfoHolder.getCurrentOrder() == null) {
      List<OrderView> orders = stream(
          restTemplate.getForEntity("/order?filterStatuses={filterStatuses}", OrderView[].class,
              Map.of("filterStatuses", "INITIATED")).getBody()).toList();
      if (!orders.isEmpty()) {
        takeOrder(orders.stream().findAny());
      }
    } else if (staticInfoHolder.getOrderInProgress() == null) {
      startOrder(staticInfoHolder.getCurrentOrder());
    } else {
      completeOrder(staticInfoHolder.getOrderInProgress());
    }
  }

  private void takeOrder(Optional<OrderView> any) {
    any.ifPresent(order -> {
      ResponseEntity<Void> response = restTemplate.exchange(
          "/order/" + order.getId() + "/TAKE?carId={carId}", HttpMethod.PATCH,
          null, Void.class,
          Map.of("carId", staticInfoHolder.getCarId()));
      if (response.getStatusCode().is2xxSuccessful()) {
        staticInfoHolder.setCurrentOrder(order.getId());
      }
    });
  }

  private void startOrder(UUID orderId) {
    ResponseEntity<Void> response = restTemplate.exchange(
        "/order/" + orderId + "/START?carId={carId}", HttpMethod.PATCH,
        null, Void.class,
        Map.of("carId", staticInfoHolder.getCarId()));
    if (response.getStatusCode().is2xxSuccessful()) {
      staticInfoHolder.setOrderInProgress(orderId);
    }
  }

  private void completeOrder(UUID orderId) {
    ResponseEntity<Void> response = restTemplate.exchange(
        "/order/" + orderId + "/COMPLETE?carId={carId}", HttpMethod.PATCH,
        null, Void.class,
        Map.of("carId", staticInfoHolder.getCarId()));
    if (response.getStatusCode().is2xxSuccessful()) {
      staticInfoHolder.setCurrentOrder(null);
      staticInfoHolder.setOrderInProgress(null);
    }
  }
}
