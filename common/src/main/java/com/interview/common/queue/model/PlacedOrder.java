package com.interview.common.queue.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlacedOrder implements Serializable {

    private String orderId;
    private long orderCreatedTime;
    private String orderStatus;
    private String personId;

    public PlacedOrder(String orderId, String orderStatus) {
        this.orderId = orderId;
        this.orderCreatedTime = System.currentTimeMillis();
        this.orderStatus = orderStatus;
    }
    
}
