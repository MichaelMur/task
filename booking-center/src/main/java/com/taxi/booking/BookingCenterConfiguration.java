package com.taxi.booking;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableJpaRepositories(basePackages = {
    "com.taxi.booking.common.db"
})
public class BookingCenterConfiguration {

}
