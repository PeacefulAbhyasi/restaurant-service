package com.interview.delivery.service.impl;

import com.interview.common.httpclient.HttpConnectionClient;
import com.interview.common.util.CommonUtil;
import com.interview.delivery.config.DeliveryConfig;
import com.interview.delivery.constants.Constants;
import com.interview.delivery.model.request.UpdateOrderRequest;
import com.interview.delivery.model.response.UpdateOrderResponse;
import com.interview.delivery.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private HttpConnectionClient httpClient;

    @Autowired
    private DeliveryConfig config;

    public UpdateOrderResponse updateOrderStatus(UpdateOrderRequest request) {
        UpdateOrderResponse response = null;
        try {
            log.info("[REQUEST] : {}", request);
            response = httpClient.sendHttpPostRequest(new StringBuilder(config.getOrderServiceBaseUrl())
                    .append(Constants.UPDATE_ORDER).toString(), CommonUtil.mapObjectToJson(
                    request), UpdateOrderResponse.class);
            log.info("[API RESPONSE] : {}", response);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return response;
    }
}
