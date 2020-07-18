package com.interview.restaurant.model.request;

import lombok.Data;

@Data
public class UpdateOrderRequest extends InternalRequest {

    private String orderId;
    private String status;
}
