package com.taxi.booking.booking.service;

import com.taxi.booking.booking.model.Action;
import com.taxi.booking.common.db.entity.OrderStatus;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class OrderActionValidator {

  private final Map<OrderStatus, Set<Action>> orderActionAllowedOperation = Map.of(
      OrderStatus.INITIATED, Set.of(Action.TAKE, Action.CANCEL),
      OrderStatus.TAKEN, Set.of(Action.START, Action.CANCEL),
      OrderStatus.IN_PROGRESS, Set.of(Action.COMPLETE),
      OrderStatus.COMPETED, Collections.emptySet()
  );


  public void validateOrderAction(OrderStatus status, Action action) {
    if (!orderActionAllowedOperation.get(status).contains(action)) {
      throw new RuntimeException("Action %s is not allowed for order".formatted(action));
    }
  }
}
