package com.interview.restaurant.service;

import com.interview.restaurant.model.common.OrderDetails;

public interface DeliveryService {

    void dispatch(String orderId, String orderStatus);

}
