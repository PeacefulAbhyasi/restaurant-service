package com.interview.restaurant.service;

import com.interview.restaurant.model.request.CreateOrderRequest;
import com.interview.restaurant.model.request.UpdateOrderRequest;
import com.interview.restaurant.model.response.CreateOrderResponse;
import com.interview.restaurant.model.response.OrderStatusResponse;
import com.interview.restaurant.model.response.UpdateOrderResponse;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest request);

    OrderStatusResponse getOrderStatus(String orderId);

    UpdateOrderResponse updateOrder(UpdateOrderRequest request);
}
