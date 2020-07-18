package com.interview.restaurant.model.common;

import com.interview.restaurant.enums.OrderStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetails implements Serializable {

    private String orderId;
    private String itemId;
    private String numberOfItems;
    private long orderCreatedTime;
    private OrderStatus orderStatus;

    public OrderDetails(String orderId, String itemId, String numberOfItems) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.numberOfItems = numberOfItems;
        this.orderCreatedTime = System.currentTimeMillis();
    }
}
