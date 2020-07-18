package com.interview.delivery.model.response;

import lombok.Data;

@Data
public class DeliveryPersonStatusResponse extends InternalResponse{

    private String status;
    private String orderId;
    private String orderStatus;
    private String remainingTime;
}
