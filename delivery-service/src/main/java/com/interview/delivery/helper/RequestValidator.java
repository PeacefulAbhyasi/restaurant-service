package com.interview.delivery.helper;

import com.interview.delivery.enums.ResultCode;
import com.interview.delivery.exceptions.RequestValidationException;
import com.interview.delivery.model.request.CreateDeliveryOrderRequest;
import com.interview.delivery.model.request.UpdateOrderDetailRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RequestValidator {

    public <T> void validateRequest(T request) throws RequestValidationException {
        if(request == null) {
            throw new RequestValidationException(ResultCode.INVALID_REQUEST);
        } else if(request instanceof String) {
            if(StringUtils.isEmpty(request)) {
                throw new RequestValidationException(ResultCode.INVALID_PARAM);
            }
        } else if(request instanceof CreateDeliveryOrderRequest) {
            CreateDeliveryOrderRequest req = (CreateDeliveryOrderRequest)request;
            if(StringUtils.isEmpty(req.getOrderId()) ||
                    StringUtils.isEmpty(req.getDeliveryPersonId())) {
                throw new RequestValidationException(ResultCode.INVALID_PARAM);
            }
        } else if (request instanceof UpdateOrderDetailRequest) {
            UpdateOrderDetailRequest req = (UpdateOrderDetailRequest) request;
            if(StringUtils.isEmpty(req.getOrderId()) || StringUtils.isEmpty(req.getOrderStatus())) {
                throw new RequestValidationException(ResultCode.INVALID_PARAM);
            }
        }
    }
}
