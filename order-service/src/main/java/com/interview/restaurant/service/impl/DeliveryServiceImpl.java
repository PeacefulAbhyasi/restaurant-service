package com.interview.restaurant.service.impl;

import com.interview.common.httpclient.HttpConnectionClient;
import com.interview.common.util.CommonUtil;
import com.interview.restaurant.config.RestaurantConfig;
import com.interview.restaurant.constants.Constants;
import com.interview.restaurant.model.request.UpdateOrderDetailRequest;
import com.interview.restaurant.model.response.UpdateOrderDetailResponse;
import com.interview.restaurant.service.DeliveryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private HttpConnectionClient httpClient;

    @Autowired
    private RestaurantConfig config;

    public void dispatch(String orderId, String orderStatus) {
        try {
            log.info("[ORDER_DETAILS] : {}", orderId);
            UpdateOrderDetailResponse response = httpClient.sendHttpPostRequest(new StringBuilder(config.getDeliveryServiceBaseUrl())
                    .append(Constants.PLACE_ORDER).toString(), CommonUtil.mapObjectToJson(
                    new UpdateOrderDetailRequest(orderId, orderStatus)), UpdateOrderDetailResponse.class);
            log.info("[API RESPONSE] : {}", response);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
    }
}
