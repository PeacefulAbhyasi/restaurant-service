package com.interview.delivery.producer;

import com.interview.common.queue.model.PlacedOrder;

public interface OrderProducer {

    void produce(PlacedOrder order);

}
