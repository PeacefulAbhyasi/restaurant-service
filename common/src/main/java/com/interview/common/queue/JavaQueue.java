package com.interview.common.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@Scope(value = ConfigurableListableBeanFactory.SCOPE_SINGLETON)
public class JavaQueue <E> {

    private BlockingQueue<E> orderQueue = new LinkedBlockingQueue();

    public E consume() {
        try {
            return orderQueue.poll(2, TimeUnit.SECONDS);
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return null;
    }

    public boolean put(E order) {
        try {
            orderQueue.add(order);
            return true;
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return false;
    }
}
