package com.interview.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantServices {

    @Autowired
    private OrderService orderService;

    public OrderService getOrderService() {
        return orderService;
    }
}
