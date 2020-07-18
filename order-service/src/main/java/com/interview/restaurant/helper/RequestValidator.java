package com.interview.restaurant.helper;

import com.interview.restaurant.enums.ResultCode;
import com.interview.restaurant.exception.RequestValidationException;
import com.interview.restaurant.model.request.CreateOrderRequest;
import com.interview.restaurant.model.request.UpdateOrderRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RequestValidator {

    public <T> void validateRequest(T request) throws RequestValidationException {
        if(request instanceof String) {
            if(StringUtils.isEmpty(request)) {
                throw new RequestValidationException(ResultCode.INVALID_PARAM);
            }
        } else if(request instanceof CreateOrderRequest) {
            CreateOrderRequest req = (CreateOrderRequest)request;
            if(StringUtils.isEmpty(req.getItemId()) ||
                    StringUtils.isEmpty(req.getNumberOfItems())) {
                throw new RequestValidationException(ResultCode.INVALID_PARAM);
            }
        } else if(request instanceof UpdateOrderRequest) {
            UpdateOrderRequest req = (UpdateOrderRequest)request;
            if(StringUtils.isEmpty(req.getOrderId()) ||
                    StringUtils.isEmpty(req.getStatus())) {
                throw new RequestValidationException(ResultCode.INVALID_PARAM);
            }
        }
    }
}
