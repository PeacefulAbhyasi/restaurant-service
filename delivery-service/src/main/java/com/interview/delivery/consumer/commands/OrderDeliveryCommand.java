package com.interview.delivery.consumer.commands;

import com.interview.common.queue.model.PlacedOrder;
import com.interview.delivery.config.ApplicationContextConfig;
import com.interview.delivery.config.DeliveryConfig;
import com.interview.delivery.dao.DeliveryDao;
import com.interview.delivery.enums.DeliveryPersonStatusEnum;
import com.interview.delivery.enums.OrderStatus;
import com.interview.delivery.model.common.DeliveryOrderDetails;
import com.interview.delivery.model.common.DeliveryPersonDetails;
import com.interview.delivery.model.request.UpdateOrderRequest;
import com.interview.delivery.service.OrderService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderDeliveryCommand implements Runnable {

    private PlacedOrder order;

    public OrderDeliveryCommand(PlacedOrder order) {
        this.order = order;
    }

    @Override
    public void run() {
        execute();
    }

    private void execute() {
        try {
            DeliveryDao deliveryDao = updatePersonDetailStatus(ApplicationContextConfig.getInstance().getBean(DeliveryDao.class));
            DeliveryOrderDetails orderDetails = createOrder(deliveryDao);
            updateOrderStatus(orderDetails);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
            throw new RuntimeException("[EXCEPTION_REQUEUE]");
        }
    }

    private DeliveryDao updatePersonDetailStatus(DeliveryDao deliveryDao) {
        DeliveryPersonDetails personDetails = deliveryDao.getPersonDetails(order.getPersonId());
        personDetails.setStatus(DeliveryPersonStatusEnum.ACTIVE);
        deliveryDao.updatePersonDetailStatus(personDetails);
        return deliveryDao;
    }

    private DeliveryOrderDetails createOrder(DeliveryDao deliveryDao) {
        DeliveryConfig config = ApplicationContextConfig.getInstance().getBean(DeliveryConfig.class);
        DeliveryOrderDetails orderDetails = new DeliveryOrderDetails(order.getOrderId(), order.getPersonId());
        orderDetails.setOrderStatus(OrderStatus.PICKED);
        orderDetails.setDeliveryTime(System.currentTimeMillis()+Long.parseLong(config.getMaxDeliveryTime()));
        deliveryDao.createOrder(orderDetails);
        return orderDetails;
    }

    private void updateOrderStatus(DeliveryOrderDetails orderDetails) {
        OrderService orderService = ApplicationContextConfig.getInstance().getBean(OrderService.class);
        orderService.updateOrderStatus(new UpdateOrderRequest(orderDetails.getOrderId(),
                orderDetails.getOrderStatus().getStatus()));
    }

}
