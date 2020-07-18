package com.interview.restaurant.repository;

import com.interview.restaurant.model.common.OrderDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class OrderRepository {

    private static ConcurrentHashMap<String, OrderDetails> orderDetailMap = new ConcurrentHashMap<>();

    public synchronized boolean save(String orderId, OrderDetails orderDetails) {
        try {
            orderDetailMap.put(orderId, orderDetails);
            return true;
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return false;
    }

    public OrderDetails get(String orderId) {
        OrderDetails orderDetails = null;
        try {
            orderDetails = orderDetailMap.get(orderId);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return orderDetails;
    }

}
