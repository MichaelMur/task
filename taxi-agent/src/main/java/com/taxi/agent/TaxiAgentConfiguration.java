package com.taxi.agent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
public class TaxiAgentConfiguration {

  @Bean
  public RestTemplate restTemplate(
      @Value("${booking.center.url}") String bookingCenterUrl) {
    return new RestTemplateBuilder()
        .rootUri(bookingCenterUrl)
        .build();
  }
}
