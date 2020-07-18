package com.interview.restaurant.enums;

import java.util.HashMap;
import java.util.Map;

public enum OrderStatus {

    ACCEPTED("ACCEPTED"), INIT("INIT"), PROCESSING("PROCESSING"), READY_FOR_DISPATCH("READY_FOR_DISPATCH"), PICKED("PICKED"), DELIVERED("DELIVERED"), CANCEL("CANCEL");

    private String status;

    OrderStatus(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    private static Map<String, OrderStatus> orderStatusMap = new HashMap<>();

    static {
        for(OrderStatus orderStatus : values()) {
            orderStatusMap.put(orderStatus.getStatus(), orderStatus);
        }
    }

    public static OrderStatus getOrderStatus(String status) {
        return orderStatusMap.get(status);
    }
}
