package com.interview.restaurant.model.response;

import lombok.Data;

@Data
public class CreateOrderResponse extends InternalResponse {

    private String orderId;
    private String status;

}
