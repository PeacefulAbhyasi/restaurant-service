package com.interview.restaurant.service.impl;

import com.interview.restaurant.constants.Constants;
import com.interview.restaurant.dao.OrderRepositoryDao;
import com.interview.restaurant.enums.OrderStatus;
import com.interview.restaurant.enums.ResultCode;
import com.interview.restaurant.exception.RequestValidationException;
import com.interview.restaurant.helper.RequestValidator;
import com.interview.restaurant.model.common.OrderDetails;
import com.interview.restaurant.model.request.CreateOrderRequest;
import com.interview.restaurant.model.request.UpdateOrderRequest;
import com.interview.restaurant.model.response.CreateOrderResponse;
import com.interview.restaurant.model.response.OrderStatusResponse;
import com.interview.restaurant.model.response.UpdateOrderResponse;
import com.interview.restaurant.pool.RestaurantExecutorServices;
import com.interview.restaurant.pool.command.DispatchOrderCommand;
import com.interview.restaurant.service.DeliveryService;
import com.interview.restaurant.service.OrderService;
import com.interview.restaurant.util.RequestResponseMapper;
import com.interview.restaurant.util.UniqueIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UniqueIdGenerator uniqueIdGenerator;

    @Autowired
    private OrderRepositoryDao orderRepositoryDao;

    @Autowired
    private RequestValidator requestValidator;

    @Autowired
    private RestaurantExecutorServices executorServices;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        log.info("[REQUEST] : {}", request);
        CreateOrderResponse response = new CreateOrderResponse();
        try {
            requestValidator.validateRequest(request);
            String orderId = new StringBuilder(Constants.ORDER_ID_PREFIX).append(uniqueIdGenerator.generateOrderId()).toString();
            OrderDetails orderDetails = new OrderDetails(orderId, request.getItemId(), request.getNumberOfItems());
            orderDetails.setOrderStatus(OrderStatus.ACCEPTED);
            response.setOrderId(orderDetails.getOrderId());
            response.setStatus(orderDetails.getOrderStatus().getStatus());
            log.info("[ORDER_DETAILS] : {}", orderDetails);
            orderRepositoryDao.save(orderDetails);
            executorServices.getInternalExecutorService().submit(
                    new DispatchOrderCommand(orderDetails.getOrderId(), orderDetails.getOrderStatus().getStatus()));
            return RequestResponseMapper.setCreateOrderResponse(response, ResultCode.ACCEPTED);
        } catch (RequestValidationException ex) {
            log.error("[EXCEPTION] [REQUEST VALIDATION] : {}", ex);
            return RequestResponseMapper.setCreateOrderResponse(response, ex.getResultCode());
        } catch (Exception ex) {
            log.error("[Exception] : {}", ex);
        }
        return RequestResponseMapper.setCreateOrderResponse(response, ResultCode.FAILED);
    }

    @Override
    public OrderStatusResponse getOrderStatus(String orderId) {
        log.info("[REQUEST] : {}", orderId);
        OrderStatusResponse response = new OrderStatusResponse();
        try {
            requestValidator.validateRequest(orderId);
            OrderDetails orderDetails = orderRepositoryDao.getOrderDetails(orderId);
            log.info("[ORDER_DETAILS] : {}", orderDetails);
            if(orderDetails == null) {
                return RequestResponseMapper.setOrderStatusResponse(response, ResultCode.NOT_FOUND);
            }
            response.setStatus(orderDetails.getOrderStatus().getStatus());
            return RequestResponseMapper.setOrderStatusResponse(response, ResultCode.SUCCESS);
        } catch (RequestValidationException ex) {
            log.error("[EXCEPTION] [REQUEST VALIDATION] : {}", ex);
            return RequestResponseMapper.setOrderStatusResponse(response, ex.getResultCode());
        } catch (Exception ex) {
            log.error("[Exception] : {}", ex);
        }
        return RequestResponseMapper.setOrderStatusResponse(response, ResultCode.FAILED);
    }

    @Override
    public UpdateOrderResponse updateOrder(UpdateOrderRequest request) {
        log.info("[REQUEST] : {}", request);
        UpdateOrderResponse response = new UpdateOrderResponse();
        try {
            requestValidator.validateRequest(request);
            OrderDetails orderDetails = orderRepositoryDao.getOrderDetails(request.getOrderId());
            if(orderDetails == null) {
                return RequestResponseMapper.setUpdateOrderStatusResponse(response, ResultCode.NOT_FOUND);
            }
            orderDetails.setOrderStatus(OrderStatus.getOrderStatus(request.getStatus()));
            response.setStatus(orderDetails.getOrderStatus().getStatus());
            log.info("[ORDER_DETAILS] : {}", orderDetails);
            orderRepositoryDao.save(orderDetails);
            return RequestResponseMapper.setUpdateOrderStatusResponse(response, ResultCode.SUCCESS);
        } catch (RequestValidationException ex) {
            log.error("[EXCEPTION] [REQUEST VALIDATION] : {}", ex);
            return RequestResponseMapper.setUpdateOrderStatusResponse(response, ex.getResultCode());
        } catch (Exception ex) {
            log.error("[Exception] : {}", ex);
        }
        return RequestResponseMapper.setUpdateOrderStatusResponse(response, ResultCode.FAILED);
    }
}
