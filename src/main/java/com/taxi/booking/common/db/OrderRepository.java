package com.taxi.booking.common.db;

import com.taxi.booking.common.db.entity.OrderEntity;
import com.taxi.booking.common.db.entity.OrderStatus;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

  List<OrderEntity> findAllByStatusIn(Collection<OrderStatus> statuses);
}
