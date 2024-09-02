package com.taxi.booking.common.db;

import com.taxi.booking.common.db.entity.CarEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, UUID> {

}
