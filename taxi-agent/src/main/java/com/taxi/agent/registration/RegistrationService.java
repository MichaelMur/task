package com.taxi.agent.registration;

import com.taxi.agent.common.StaticInfoHolder;
import com.taxi.agent.registration.model.TaxiInfo;
import com.taxi.agent.telemetry.GeoService;
import jakarta.annotation.PreDestroy;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RegistrationService {

  private final RestTemplate restTemplate;
  private final StaticInfoHolder staticInfoHolder;

  public RegistrationService(RestTemplate restTemplate, StaticInfoHolder staticInfoHolder) {
    this.restTemplate = restTemplate;
    this.staticInfoHolder = staticInfoHolder;
  }

  public void register() {
    TaxiInfo request = new TaxiInfo();
    request.setDriverId(staticInfoHolder.getDriverId());
    request.setDriverName(staticInfoHolder.getDriverName());
    request.setLat(GeoService.randomLatitude());
    request.setLon(GeoService.randomLongitude());
    UUID carId = restTemplate.postForObject("/taxi/registration", request, UUID.class);
    staticInfoHolder.setCarId(carId);
  }

  @PreDestroy
  public void onDestroy() {
    restTemplate.postForLocation("/taxi/stop", staticInfoHolder.getCarId());
    if (staticInfoHolder.getCurrentOrder() != null) {
      ResponseEntity<Void> response = restTemplate.exchange(
          "/order/" + staticInfoHolder.getCurrentOrder() + "/CANCEL?carId={carId}",
          HttpMethod.PATCH,
          null, Void.class,
          Map.of("carId", staticInfoHolder.getCarId()));
    }

    if (staticInfoHolder.getOrderInProgress() != null) {
      ResponseEntity<Void> response = restTemplate.exchange(
          "/order/" + staticInfoHolder.getOrderInProgress() + "/CANCEL?carId={carId}",
          HttpMethod.PATCH,
          null, Void.class,
          Map.of("carId", staticInfoHolder.getCarId()));
    }
  }
}
