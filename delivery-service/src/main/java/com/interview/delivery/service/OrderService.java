package com.interview.delivery.service;

import com.interview.delivery.model.request.UpdateOrderRequest;
import com.interview.delivery.model.response.UpdateOrderResponse;

public interface OrderService {

    UpdateOrderResponse updateOrderStatus(UpdateOrderRequest request);

}
