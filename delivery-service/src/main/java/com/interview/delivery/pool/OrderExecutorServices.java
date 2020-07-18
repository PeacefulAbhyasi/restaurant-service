package com.interview.delivery.pool;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class OrderExecutorServices {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, new ThreadFactoryBuilder()
            .setNamePrefix("SCHEDULED-THREAD").setPriority(Thread.NORM_PRIORITY).setDaemon(false).build());


    private ExecutorService consumerExecutorService = new ThreadPoolExecutor(25, 50,
            60l, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder()
            .setNamePrefix("ORDER-THREAD").setPriority(Thread.NORM_PRIORITY).setDaemon(false).build(),
            (Runnable runnable, ThreadPoolExecutor executor) -> {
                throw new RuntimeException("EXCEPTION");
            });

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public ExecutorService getConsumerExecutorService() {
        return consumerExecutorService;
    }
}
