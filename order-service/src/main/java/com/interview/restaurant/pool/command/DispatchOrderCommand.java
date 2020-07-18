package com.interview.restaurant.pool.command;

import com.interview.common.httpclient.HttpConnectionClient;
import com.interview.common.util.CommonUtil;
import com.interview.restaurant.config.ApplicationContextConfig;
import com.interview.restaurant.constants.Constants;
import com.interview.restaurant.model.request.UpdateOrderDetailRequest;
import com.interview.restaurant.model.response.UpdateOrderDetailResponse;
import com.interview.restaurant.service.DeliveryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class DispatchOrderCommand implements Runnable {

    private String orderId;
    private String orderStatus;

    @Override
    public void run() {
        execute();
    }

    private void execute() {
        try {
            log.info("[ORDER_ID] : {}, [ORDER_STATUS] : {}", orderId, orderStatus);
            ApplicationContextConfig.getInstance().getBean(DeliveryService.class).dispatch(orderId, orderStatus);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
    }
}
