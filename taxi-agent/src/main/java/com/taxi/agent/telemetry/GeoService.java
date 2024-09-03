package com.taxi.agent.telemetry;

import com.taxi.agent.common.StaticInfoHolder;
import com.taxi.agent.telemetry.model.Geo;
import java.util.UUID;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoService {

  private final RestTemplate restTemplate;
  private final StaticInfoHolder staticInfoHolder;

  public GeoService(RestTemplate restTemplate, StaticInfoHolder staticInfoHolder) {
    this.restTemplate = restTemplate;
    this.staticInfoHolder = staticInfoHolder;
  }

  public static double randomLatitude() {
    return Math.random() * 90;
  }

  public static double randomLongitude() {
    return Math.random() * 180;
  }

  @Scheduled(fixedRate = 1000)
  public void report() {
    UUID carId = staticInfoHolder.getCarId();
    if (carId != null) {
      restTemplate.postForLocation("/car/" + staticInfoHolder.getCarId() + "/geo",
          new Geo(randomLatitude(), randomLatitude()));
    }
  }
}
