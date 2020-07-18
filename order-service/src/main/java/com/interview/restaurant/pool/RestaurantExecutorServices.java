package com.interview.restaurant.pool;

import org.springframework.stereotype.Component;

import java.util.concurrent.*;

@Component
public class RestaurantExecutorServices {

    private ExecutorService internalExecutorService = new ThreadPoolExecutor(25, 50,
            60l, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder()
            .setNamePrefix("THREAD-POOL").setPriority(Thread.NORM_PRIORITY).setDaemon(false).build(),
            (Runnable runnable, ThreadPoolExecutor executor) -> {
                throw new RuntimeException("EXCEPTION");
            });

    public ExecutorService getInternalExecutorService() {
        return internalExecutorService;
    }
}
