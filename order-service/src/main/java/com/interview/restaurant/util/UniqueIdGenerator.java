package com.interview.restaurant.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class UniqueIdGenerator {

    private AtomicLong orderIdCount = new AtomicLong(0l);

    public long generateOrderId() {
        return orderIdCount.addAndGet(1l);
    }
}
