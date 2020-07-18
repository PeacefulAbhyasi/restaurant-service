package com.interview.delivery.model.request;

import lombok.Data;

@Data
public class CreateDeliveryOrderRequest extends InternalRequest {

    private String orderId;
    private String deliveryPersonId;

}
