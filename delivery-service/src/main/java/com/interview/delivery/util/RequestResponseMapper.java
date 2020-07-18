package com.interview.delivery.util;

import com.interview.delivery.enums.ResultCode;
import com.interview.delivery.model.response.CreateDeliveryOrderResponse;
import com.interview.delivery.model.response.DeliveryPersonResponse;
import com.interview.delivery.model.response.DeliveryPersonStatusResponse;
import com.interview.delivery.model.response.UpdateOrderDetailResponse;

public class RequestResponseMapper {

    public static CreateDeliveryOrderResponse setCreateDeliveryOrderResponse(CreateDeliveryOrderResponse response,
                                                                             ResultCode resultCode) {
        response.setResultCodeId(resultCode.getResultCodeId());
        response.setResultCode(resultCode.getResultCode());
        response.setResultCodeMsg(resultCode.getResultCodeMsg());
        return response;
    }

    public static DeliveryPersonStatusResponse setPersonStatusResponse(DeliveryPersonStatusResponse response,
                                                                              ResultCode resultCode) {
        response.setResultCodeId(resultCode.getResultCodeId());
        response.setResultCode(resultCode.getResultCode());
        response.setResultCodeMsg(resultCode.getResultCodeMsg());
        return response;
    }

    public static DeliveryPersonResponse setDeliveryPersonResponse(DeliveryPersonResponse response,
                                                                         ResultCode resultCode) {
        response.setResultCodeId(resultCode.getResultCodeId());
        response.setResultCode(resultCode.getResultCode());
        response.setResultCodeMsg(resultCode.getResultCodeMsg());
        return response;
    }

    public static UpdateOrderDetailResponse setUpdateOrderDetailResponse(UpdateOrderDetailResponse response,
                                                                      ResultCode resultCode) {
        response.setResultCodeId(resultCode.getResultCodeId());
        response.setResultCode(resultCode.getResultCode());
        response.setResultCodeMsg(resultCode.getResultCodeMsg());
        return response;
    }
}
