package com.interview.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryServices {

    @Autowired
    private DeliveryService deliveryService;

    public DeliveryService getDeliveryService() {
        return deliveryService;
    }
}
