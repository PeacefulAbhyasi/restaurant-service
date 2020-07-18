package com.interview.delivery.service;

import com.interview.delivery.model.request.CreateDeliveryOrderRequest;
import com.interview.delivery.model.request.UpdateOrderDetailRequest;
import com.interview.delivery.model.response.CreateDeliveryOrderResponse;
import com.interview.delivery.model.response.DeliveryPersonResponse;
import com.interview.delivery.model.response.DeliveryPersonStatusResponse;
import com.interview.delivery.model.response.UpdateOrderDetailResponse;

public interface DeliveryService {

    CreateDeliveryOrderResponse createOrder(CreateDeliveryOrderRequest request);

    DeliveryPersonStatusResponse getPersonStatus(String personId);

    DeliveryPersonResponse getAllPersonDetails();

    UpdateOrderDetailResponse placeOrder(UpdateOrderDetailRequest request);

    UpdateOrderDetailResponse updateOrder(UpdateOrderDetailRequest request);
}
