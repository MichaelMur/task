package com.taxi.agent;

import com.taxi.agent.common.StaticInfoHolder;
import com.taxi.agent.registration.RegistrationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TaxiAgent {

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(TaxiAgent.class, args);
    StaticInfoHolder infoHolder = context.getBean(StaticInfoHolder.class);
    infoHolder.setDriverId(args[0]);
    infoHolder.setDriverName(args[1]);

    RegistrationService registration = context.getBean(RegistrationService.class);
    registration.register();
  }
}
