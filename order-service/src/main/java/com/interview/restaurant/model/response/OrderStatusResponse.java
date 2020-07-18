package com.interview.restaurant.model.response;

import lombok.Data;

@Data
public class OrderStatusResponse extends InternalResponse {

    private String status;
}
