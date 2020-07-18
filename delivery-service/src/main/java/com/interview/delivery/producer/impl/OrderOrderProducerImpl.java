package com.interview.delivery.producer.impl;

import com.interview.common.queue.model.PlacedOrder;
import com.interview.common.queue.service.OrderDeliveryService;
import com.interview.delivery.producer.OrderProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderOrderProducerImpl implements OrderProducer {

    @Autowired
    private OrderDeliveryService orderDeliveryService;

    public void produce(PlacedOrder order) {
        try {
            orderDeliveryService.putOrderData(order);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
    }
}
