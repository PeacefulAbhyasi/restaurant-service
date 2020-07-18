package com.interview.delivery.consumer;

import com.interview.common.queue.model.PlacedOrder;
import com.interview.common.queue.service.OrderDeliveryService;
import com.interview.delivery.consumer.commands.OrderDeliveryCommand;
import com.interview.delivery.dao.DeliveryDao;
import com.interview.delivery.enums.DeliveryPersonStatusEnum;
import com.interview.delivery.model.common.DeliveryPersonStatus;
import com.interview.delivery.pool.OrderExecutorServices;
import com.interview.delivery.producer.OrderProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.*;

/**
 * @since 1.0
 * This class consumes data from OrderQueue which contains placed order.
 * Consumer assigns order to available delivery person.
 * If delivery person is not available then consumer will not poll
 * data from queue. If any exception occur while assigning
 * delivery person then it will requeue the order.
 *
 */

@Slf4j
@Component
public class OrderConsumer {

    @Autowired
    private OrderExecutorServices orderExecutorServices;

    @Autowired
    private OrderDeliveryService orderDeliveryService;

    @Autowired
    private OrderProducer producer;

    @Autowired
    private DeliveryDao deliveryDao;

    public void initialize() {
        ScheduledExecutorService executorService = orderExecutorServices.getScheduledExecutorService();
        executorService.scheduleAtFixedRate(() -> {
            consume();
        }, 10, 2, TimeUnit.SECONDS);
    }

    private void consume() {
        try {
            List<DeliveryPersonStatus> list = deliveryDao.getPersonDetails(DeliveryPersonStatusEnum.AVAILABLE);
            if(list == null || list.size() <= 0) {
                log.info("[NO DELIVERY PERSON AVAILABLE]");
                return;
            }
            PlacedOrder placedOrder = orderDeliveryService.getOrderData();
            if(placedOrder == null) {
                log.debug("[NO ORDER PLACED]");
                return;
            }
            CompletableFuture.runAsync(new OrderDeliveryCommand(setDetails(placedOrder, list.get(0))),
                    orderExecutorServices.getConsumerExecutorService()).exceptionally(ex -> {
                log.error("[CONSUMER EXCEPTION] : {}", ex);
                produce(placedOrder);
                return null;
            });
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
    }

    private PlacedOrder setDetails(PlacedOrder placedOrder, DeliveryPersonStatus deliveryPerson) {
        placedOrder.setPersonId(deliveryPerson.getPersonId());
        log.info("[PLACED_ORDER] : {}", placedOrder);
        return placedOrder;
    }

    private void produce(PlacedOrder order) {
        log.info("[RE_QUEUE DATA] : {}", order);
        try {
            producer.produce(order);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
    }
}
