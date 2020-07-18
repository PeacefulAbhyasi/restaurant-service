package com.interview.delivery.config;

import com.interview.delivery.constants.Constants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Configuration
@PropertySource("classpath:application.properties")
public class DeliveryConfig {

    @Value(Constants.DELIVERY_PERSON_COUNT)
    private String deliveryPersons;

    @Value(Constants.ORDER_SERVICE_BASE_URL)
    private String orderServiceBaseUrl;

    @Value(Constants.MAX_DELIVERY_TIME)
    private String maxDeliveryTime;
}
