package com.interview.common.queue.service.impl;

import com.interview.common.queue.JavaQueue;
import com.interview.common.queue.model.PlacedOrder;
import com.interview.common.queue.service.OrderDeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderDeliveryServiceImpl implements OrderDeliveryService {

    @Autowired
    private JavaQueue<PlacedOrder> javaQueue;

    @Override
    public PlacedOrder getOrderData() {
        PlacedOrder placedOrder = null;
        try {
            placedOrder = javaQueue.consume();
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return placedOrder;
    }

    public void putOrderData(PlacedOrder order) {
        try {
            javaQueue.put(order);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
    }
}
