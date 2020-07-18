package com.interview.restaurant.util;

import com.interview.restaurant.enums.ResultCode;
import com.interview.restaurant.model.response.CreateOrderResponse;
import com.interview.restaurant.model.response.OrderStatusResponse;
import com.interview.restaurant.model.response.UpdateOrderResponse;

public class RequestResponseMapper {

    public static CreateOrderResponse setCreateOrderResponse(CreateOrderResponse response, ResultCode resultCode) {
        response.setResultCode(resultCode.getResultCode());
        response.setResultCodeId(resultCode.getResultCodeId());
        response.setResultCodeMsg(resultCode.getResultCodeMsg());
        return response;
    }

    public static OrderStatusResponse setOrderStatusResponse(OrderStatusResponse response, ResultCode resultCode) {
        response.setResultCode(resultCode.getResultCode());
        response.setResultCodeId(resultCode.getResultCodeId());
        response.setResultCodeMsg(resultCode.getResultCodeMsg());
        return response;
    }

    public static UpdateOrderResponse setUpdateOrderStatusResponse(UpdateOrderResponse response, ResultCode resultCode) {
        response.setResultCode(resultCode.getResultCode());
        response.setResultCodeId(resultCode.getResultCodeId());
        response.setResultCodeMsg(resultCode.getResultCodeMsg());
        return response;
    }
}
