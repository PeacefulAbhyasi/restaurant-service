package com.interview.delivery.model.common;

import com.interview.delivery.constants.Constants;
import com.interview.delivery.enums.OrderStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class DeliveryOrderDetails implements Serializable {

    private String orderId;
    private OrderStatus orderStatus;
    private String deliveryPersonId;
    private long deliveryTime;

    public DeliveryOrderDetails(String orderId, String personId) {
        this.orderId = orderId;
        this.deliveryPersonId = personId;
    }

    public String getKey() {
        return new StringBuilder(Constants.ORDER_DETAIL_PREFIX).append(orderId).append("_").append(deliveryPersonId).toString();
    }
}
