package com.interview.restaurant.dao.impl;

import com.interview.restaurant.dao.OrderRepositoryDao;
import com.interview.restaurant.enums.OrderStatus;
import com.interview.restaurant.model.common.OrderDetails;
import com.interview.restaurant.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Slf4j
@Repository
public class OrderRepositoryDaoImpl implements OrderRepositoryDao {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public boolean save(OrderDetails orderDetails) {
        return orderRepository.save(orderDetails.getOrderId(), orderDetails);
    }

    @Override
    public OrderDetails getOrderDetails(String orderId) {
        return orderRepository.get(orderId);
    }

    @Override
    public boolean update(String orderId, String status) {
        try {
            OrderDetails orderDetails = orderRepository.get(orderId);
            if(orderDetails == null) {
                return false;
            }
            orderDetails.setOrderStatus(OrderStatus.getOrderStatus(status));
            orderRepository.save(orderDetails.getOrderId(), orderDetails);
            return true;
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return false;
    }
}
