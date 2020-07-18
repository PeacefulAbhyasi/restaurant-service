package com.interview.restaurant.config;

import com.interview.restaurant.constants.Constants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:application.properties")
public class RestaurantConfig {

    @Value(Constants.DELIVERY_SERVICE_BASE_URL)
    private String deliveryServiceBaseUrl;
}
