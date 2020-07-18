package com.interview.delivery.dao;

import com.interview.delivery.enums.DeliveryPersonStatusEnum;
import com.interview.delivery.model.common.DeliveryOrderDetails;
import com.interview.delivery.model.common.DeliveryPersonDetails;
import com.interview.delivery.model.common.DeliveryPersonStatus;

import java.util.List;

public interface DeliveryDao {

    DeliveryOrderDetails getOrderDetails(String key);

    DeliveryOrderDetails getOrderDetailsByOrderId(String orderId);

    DeliveryOrderDetails getOrderDetailsByPersonId(String personId);

    void removeOrderDetailByPersonId(String personId);

    boolean createOrder(DeliveryOrderDetails deliveryOrderDetails);

    boolean updateOrder(DeliveryOrderDetails orderDetails);

    DeliveryPersonDetails getPersonDetails(String personId);

    List<DeliveryPersonStatus> getPersonDetails();

    List<DeliveryPersonStatus> getPersonDetails(DeliveryPersonStatusEnum personStatusEnum);

    boolean updatePersonDetailStatus(DeliveryPersonDetails personDetails);

    List<DeliveryPersonDetails> getDeliveryPersonDetails(DeliveryPersonStatusEnum personStatusEnum);
}
