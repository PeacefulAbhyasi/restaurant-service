package com.interview.delivery.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateOrderRequest extends InternalRequest {

    private String orderId;
    private String status;
}
