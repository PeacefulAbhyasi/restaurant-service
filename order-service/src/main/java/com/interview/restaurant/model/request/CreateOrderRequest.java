package com.interview.restaurant.model.request;

import lombok.Data;

@Data
public class CreateOrderRequest extends InternalRequest {

    private String itemId;
    private String numberOfItems;
}
