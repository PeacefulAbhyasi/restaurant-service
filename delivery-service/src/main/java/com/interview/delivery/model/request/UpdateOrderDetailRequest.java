package com.interview.delivery.model.request;

import lombok.Data;

@Data
public class UpdateOrderDetailRequest extends InternalRequest {

    private String orderId;
    private String orderStatus;
    private String personId;
}
