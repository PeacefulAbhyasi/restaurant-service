package com.interview.restaurant.controller;

import com.interview.restaurant.constants.ApiConstants;
import com.interview.restaurant.model.common.MerchantInternalResponse;
import com.interview.restaurant.model.request.CreateOrderRequest;
import com.interview.restaurant.model.request.UpdateOrderRequest;
import com.interview.restaurant.service.RestaurantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.BASE_URL)
public class OrderCheckoutController {

    @Autowired
    private RestaurantServices services;

    @GetMapping(ApiConstants.ORDER_STATUS)
    public ResponseEntity<MerchantInternalResponse> orderStatus(@PathVariable(ApiConstants.ORDER_ID_PARAM) String orderId) {
        return  new ResponseEntity(services.getOrderService().getOrderStatus(orderId), HttpStatus.OK);
    }

    @PostMapping(ApiConstants.CREATE_ORDER)
    public ResponseEntity<MerchantInternalResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return  new ResponseEntity(services.getOrderService().createOrder(request), HttpStatus.OK);
    }

    @PostMapping(ApiConstants.UPDATE_ORDER)
    public ResponseEntity<MerchantInternalResponse> updateOrder(@RequestBody UpdateOrderRequest request) {
        return  new ResponseEntity(services.getOrderService().updateOrder(request), HttpStatus.OK);
    }
}
