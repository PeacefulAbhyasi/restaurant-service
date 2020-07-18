package com.interview.restaurant.dao;

import com.interview.restaurant.model.common.OrderDetails;

public interface OrderRepositoryDao {

    boolean save(OrderDetails orderDetails);

    boolean update(String orderId, String status);

    OrderDetails getOrderDetails(String orderId);
}
