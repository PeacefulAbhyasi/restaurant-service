package com.interview.restaurant.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateOrderDetailRequest extends InternalRequest {

    private String orderId;
    private String orderStatus;
}
