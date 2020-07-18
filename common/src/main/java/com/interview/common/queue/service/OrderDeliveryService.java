package com.interview.common.queue.service;

import com.interview.common.queue.model.PlacedOrder;

public interface OrderDeliveryService {

    PlacedOrder getOrderData();

    void putOrderData(PlacedOrder order);
}
