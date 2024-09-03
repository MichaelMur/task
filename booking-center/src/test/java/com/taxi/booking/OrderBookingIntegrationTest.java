package com.taxi.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.taxi.booking.booking.model.OrderView;
import com.taxi.booking.cars.model.CarStatusInfo;
import com.taxi.booking.cars.model.TaxiStatus;
import com.taxi.booking.common.Geo;
import com.taxi.booking.common.db.entity.OrderStatus;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = BookingCenterApp.class
)
public class OrderBookingIntegrationTest extends AbstractIntegrationTest {

  @Test
  public void shouldProcessOrder() throws Exception {

    List<CarStatusInfo> cars = loadAllCars();
    assertTrue(cars.isEmpty());

    UUID car1 = registerNewCar("d001", "driver_1", randomLatitude(), randomLongitude());
    UUID car2 = registerNewCar("d002", "driver_2", randomLatitude(), randomLongitude());

    cars = loadAllCars();
    assertEquals(cars.size(), 2);
    assertTrue(cars.stream()
        .map(CarStatusInfo::getStatus)
        .allMatch(s -> s.equals(TaxiStatus.AVAILABLE)));

    List<OrderView> orders = loadAllOrders();
    assertTrue(orders.isEmpty());

    UUID order1 = registerNewOrder(
        new Geo(randomLatitude(), randomLongitude()),
        new Geo(randomLatitude(), randomLongitude())
    );

    UUID order2 = registerNewOrder(
        new Geo(randomLatitude(), randomLongitude()),
        new Geo(randomLatitude(), randomLongitude())
    );

    orders = loadAllOrders();
    assertEquals(orders.size(), 2);
    assertTrue(orders.stream()
        .map(OrderView::getStatus)
        .allMatch(s -> s.equals(OrderStatus.INITIATED)));

    takeOrderBy(order1, car1);
    orders = loadAllOrders();
    assertTrue(orders.stream()
        .filter(orderView -> orderView.getId().equals(order1))
        .map(OrderView::getStatus)
        .allMatch(s -> s.equals(OrderStatus.TAKEN)));

    cars = loadAllCars();
    assertTrue(cars.stream()
        .filter(car -> car.getId().equals(car1))
        .map(CarStatusInfo::getStatus)
        .allMatch(s -> s.equals(TaxiStatus.BOOKED)));

    startOrderBy(order1, car1);
    completeOrderBy(order1, car1);
    orders = loadAllOrders();
    assertTrue(orders.stream()
        .filter(orderView -> orderView.getId().equals(order1))
        .map(OrderView::getStatus)
        .allMatch(s -> s.equals(OrderStatus.COMPETED)));

    cars = loadAllCars();
    assertTrue(cars.stream()
        .map(CarStatusInfo::getStatus)
        .allMatch(s -> s.equals(TaxiStatus.AVAILABLE)));
  }
}
