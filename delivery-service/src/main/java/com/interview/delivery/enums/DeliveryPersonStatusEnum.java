package com.interview.delivery.enums;

public enum DeliveryPersonStatusEnum {

    AVAILABLE("AVAILABLE"), ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

    private String status;

    DeliveryPersonStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
