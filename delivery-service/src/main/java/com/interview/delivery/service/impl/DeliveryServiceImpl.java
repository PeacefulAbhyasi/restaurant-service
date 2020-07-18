package com.interview.delivery.service.impl;

import com.interview.common.queue.model.PlacedOrder;
import com.interview.delivery.config.DeliveryConfig;
import com.interview.delivery.dao.DeliveryDao;
import com.interview.delivery.enums.DeliveryPersonStatusEnum;
import com.interview.delivery.enums.OrderStatus;
import com.interview.delivery.enums.ResultCode;
import com.interview.delivery.exceptions.RequestValidationException;
import com.interview.delivery.model.common.DeliveryOrderDetails;
import com.interview.delivery.model.common.DeliveryPersonDetails;
import com.interview.delivery.model.common.DeliveryPersonStatus;
import com.interview.delivery.model.request.CreateDeliveryOrderRequest;
import com.interview.delivery.model.request.UpdateOrderDetailRequest;
import com.interview.delivery.model.request.UpdateOrderRequest;
import com.interview.delivery.model.response.CreateDeliveryOrderResponse;
import com.interview.delivery.model.response.DeliveryPersonResponse;
import com.interview.delivery.model.response.DeliveryPersonStatusResponse;
import com.interview.delivery.model.response.UpdateOrderDetailResponse;
import com.interview.delivery.producer.OrderProducer;
import com.interview.delivery.service.DeliveryService;
import com.interview.delivery.service.OrderService;
import com.interview.delivery.util.RequestResponseMapper;
import com.interview.delivery.helper.RequestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * @since 1.0
 */

@Slf4j
@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private DeliveryDao deliveryDao;

    @Autowired
    private RequestValidator requestValidator;

    @Autowired
    private OrderProducer orderProducer;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DeliveryConfig config;

    /**
     * @return CreateDeliveryOrderResponse
     * @param request
     *
     */
    @Override
    public CreateDeliveryOrderResponse createOrder(CreateDeliveryOrderRequest request) {
        log.info("[REQUEST] : {}", request);
        CreateDeliveryOrderResponse response = new CreateDeliveryOrderResponse();
        try {
            requestValidator.validateRequest(request);
            DeliveryOrderDetails orderDetails = new DeliveryOrderDetails(request.getOrderId(), request.getDeliveryPersonId());
            DeliveryOrderDetails orderDetailData = deliveryDao.getOrderDetails(orderDetails.getKey());
            if(orderDetailData != null) {
                return RequestResponseMapper.setCreateDeliveryOrderResponse(response, ResultCode.IDEMPOTENT_ERROR);
            }
            List<DeliveryPersonDetails> list = deliveryDao.getDeliveryPersonDetails(DeliveryPersonStatusEnum.AVAILABLE);
            if(list == null || list.size() <= 0) {
                return RequestResponseMapper.setCreateDeliveryOrderResponse(response, ResultCode.DELIVERY_PERSON_NOT_AVAILABLE);
            }
            DeliveryPersonDetails personDetails = list.get(0);
            personDetails.setStatus(DeliveryPersonStatusEnum.ACTIVE);
            deliveryDao.updatePersonDetailStatus(personDetails);
            orderDetails.setOrderStatus(OrderStatus.READY_FOR_DISPATCH);
            orderDetails.setDeliveryTime(System.currentTimeMillis()+Long.parseLong(config.getMaxDeliveryTime()));
            log.info("[ORDER_DETAILS] : {}", orderDetails);
            deliveryDao.createOrder(orderDetails);
            response.setStatus(OrderStatus.ACCEPTED.getStatus());
            return RequestResponseMapper.setCreateDeliveryOrderResponse(response, ResultCode.ACCEPTED);
        } catch (RequestValidationException ex ) {
            log.error("[RequestValidationException] : {}", ex);
            return RequestResponseMapper.setCreateDeliveryOrderResponse(response, ex.getResultCode());
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return RequestResponseMapper.setCreateDeliveryOrderResponse(response, ResultCode.FAILED);
    }

    /**
     * @return DeliveryPersonStatusResponse
     * @param personId
     *
     */
    @Override
    public DeliveryPersonStatusResponse getPersonStatus(String personId) {
        log.info("[REQUEST] : {}", personId);
        DeliveryPersonStatusResponse response = new DeliveryPersonStatusResponse();
        try {
            requestValidator.validateRequest(personId);
            DeliveryPersonDetails personDetails = deliveryDao.getPersonDetails(personId);
            if(personDetails == null) {
                return RequestResponseMapper.setPersonStatusResponse(response, ResultCode.NOT_FOUND);
            }
            log.info("[PERSON_DETAILS] : {}", personDetails);
            response.setStatus(personDetails.getStatus().getStatus());
            DeliveryOrderDetails orderDetails = deliveryDao.getOrderDetailsByPersonId(personId);
            if(orderDetails != null
                    && DeliveryPersonStatusEnum.ACTIVE.getStatus().equals(personDetails.getStatus().getStatus())) {
                response.setOrderId(orderDetails.getOrderId());
                response.setOrderStatus(orderDetails.getOrderStatus().getStatus());
                long timeLeft = (orderDetails.getDeliveryTime()-System.currentTimeMillis())/60000;
                timeLeft = (timeLeft<0) ? 0 : timeLeft;
                response.setRemainingTime(String.valueOf(timeLeft));
            }
            return RequestResponseMapper.setPersonStatusResponse(response, ResultCode.SUCCESS);
        } catch (RequestValidationException ex ) {
            log.error("[RequestValidationException] : {}", ex);
            return RequestResponseMapper.setPersonStatusResponse(response, ex.getResultCode());
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return RequestResponseMapper.setPersonStatusResponse(response, ResultCode.FAILED);
    }

    /**
     * @return DeliveryPersonResponse
     *
     */
    @Override
    public DeliveryPersonResponse getAllPersonDetails() {
        DeliveryPersonResponse response = new DeliveryPersonResponse();
        try {
            List<DeliveryPersonStatus> personStatuses = deliveryDao.getPersonDetails();
            if(personStatuses == null || personStatuses.size() == 0) {
                return RequestResponseMapper.setDeliveryPersonResponse(response, ResultCode.NOT_FOUND);
            }
            log.info("[PERSON_DETAILS_SIZE] : {}", personStatuses.size());
            response.setStatus(personStatuses);
            return RequestResponseMapper.setDeliveryPersonResponse(response, ResultCode.SUCCESS);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return RequestResponseMapper.setDeliveryPersonResponse(response, ResultCode.FAILED);
    }

    /**
     * @return UpdateOrderDetailResponse
     *
     */
    public UpdateOrderDetailResponse placeOrder(UpdateOrderDetailRequest request) {
        UpdateOrderDetailResponse response = new UpdateOrderDetailResponse();
        try {
            requestValidator.validateRequest(request);
            orderProducer.produce(new PlacedOrder(request.getOrderId(), request.getOrderStatus()));
            return RequestResponseMapper.setUpdateOrderDetailResponse(response, ResultCode.SUCCESS);
        } catch (RequestValidationException ex ) {
            log.error("[RequestValidationException] : {}", ex);
            return RequestResponseMapper.setUpdateOrderDetailResponse(response, ex.getResultCode());
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return RequestResponseMapper.setUpdateOrderDetailResponse(response, ResultCode.FAILED);
    }

    /**
     * @return UpdateOrderDetailResponse
     *
     */
    public UpdateOrderDetailResponse updateOrder(UpdateOrderDetailRequest request) {
        UpdateOrderDetailResponse response = new UpdateOrderDetailResponse();
        DeliveryOrderDetails orderDetails = new DeliveryOrderDetails(request.getOrderId(), request.getPersonId());
        try {
            requestValidator.validateRequest(request);
            if(StringUtils.isEmpty(request.getPersonId())) {
                throw new RequestValidationException(ResultCode.INVALID_PARAM);
            }
            DeliveryPersonDetails personDetails = deliveryDao.getPersonDetails(request.getPersonId());
            if(personDetails == null) {
                return RequestResponseMapper.setUpdateOrderDetailResponse(response, ResultCode.DELIVERY_PERSON_NOT_AVAILABLE);
            }
            orderDetails = deliveryDao.getOrderDetails(orderDetails.getKey());
            if(orderDetails == null) {
                return RequestResponseMapper.setUpdateOrderDetailResponse(response, ResultCode.NOT_FOUND);
            }
            if(OrderStatus.DELIVERED.getStatus().equals(orderDetails.getOrderStatus().getStatus())
                    || OrderStatus.CANCEL.getStatus().equals(orderDetails.getOrderStatus().getStatus())) {
                return RequestResponseMapper.setUpdateOrderDetailResponse(response, ResultCode.INVALID_PARAM);
            }
            orderDetails.setOrderStatus(OrderStatus.getOrderStatus(request.getOrderStatus()));
            if(OrderStatus.DELIVERED.getStatus().equals(request.getOrderStatus())
                    || OrderStatus.CANCEL.getStatus().equals(request.getOrderStatus())) {
                personDetails.setStatus(DeliveryPersonStatusEnum.AVAILABLE);
                deliveryDao.updatePersonDetailStatus(personDetails);
            }
            deliveryDao.updateOrder(orderDetails);
            orderService.updateOrderStatus(new UpdateOrderRequest(orderDetails.getOrderId(),
                    orderDetails.getOrderStatus().getStatus()));
            return RequestResponseMapper.setUpdateOrderDetailResponse(response, ResultCode.SUCCESS);
        } catch (RequestValidationException ex ) {
            log.error("[RequestValidationException] : {}", ex);
            return RequestResponseMapper.setUpdateOrderDetailResponse(response, ex.getResultCode());
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return RequestResponseMapper.setUpdateOrderDetailResponse(response, ResultCode.FAILED);
    }
}
