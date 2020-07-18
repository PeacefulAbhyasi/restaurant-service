package com.interview.delivery.controller;

import com.interview.delivery.constants.ApiConstants;
import com.interview.delivery.model.common.MerchantInternalResponse;
import com.interview.delivery.model.request.CreateDeliveryOrderRequest;
import com.interview.delivery.model.request.UpdateOrderDetailRequest;
import com.interview.delivery.service.DeliveryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.BASE_URL)
public class DeliveryPersonController {

    @Autowired
    private DeliveryServices services;

    @GetMapping(ApiConstants.DELIVERY_STATUS)
    public ResponseEntity<MerchantInternalResponse> deliveryStatus(@PathVariable(ApiConstants.DELIVERY_PERSON_ID_PARAM) String deliveryPersonId) {
        return  new ResponseEntity(services.getDeliveryService().getPersonStatus(deliveryPersonId), HttpStatus.OK);
    }

    @PostMapping(ApiConstants.CREATE_DELIVERY)
    public ResponseEntity<MerchantInternalResponse> createOrder(@RequestBody CreateDeliveryOrderRequest request) {
        return  new ResponseEntity(services.getDeliveryService().createOrder(request), HttpStatus.OK);
    }

    @GetMapping(ApiConstants.ALL_DELIVERY_PERSON_STATUS)
    public ResponseEntity<MerchantInternalResponse> deliveryStatus() {
        return  new ResponseEntity(services.getDeliveryService().getAllPersonDetails(), HttpStatus.OK);
    }

    @PostMapping(ApiConstants.PLACE_ORDER)
    public ResponseEntity<MerchantInternalResponse> placeOrder(@RequestBody UpdateOrderDetailRequest request) {
        return  new ResponseEntity(services.getDeliveryService().placeOrder(request), HttpStatus.OK);
    }

    @PostMapping(ApiConstants.UPDATE_ORDER)
    public ResponseEntity<MerchantInternalResponse> updateOrder(@RequestBody UpdateOrderDetailRequest request) {
        return  new ResponseEntity(services.getDeliveryService().updateOrder(request), HttpStatus.OK);
    }
}
