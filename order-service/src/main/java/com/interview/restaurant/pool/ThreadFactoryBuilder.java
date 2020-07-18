package com.interview.restaurant.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class ThreadFactoryBuilder {

    private String namePrefix = "Consumer";
    private boolean daemon = false;
    private int priority = Thread.NORM_PRIORITY;

    public ThreadFactoryBuilder setNamePrefix(String namePrefix) {
        if (namePrefix != null) {
            this.namePrefix = namePrefix;
        }
        return this;
    }

    public ThreadFactoryBuilder setDaemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public ThreadFactoryBuilder setPriority(int priority) {
        if (priority < Thread.MIN_PRIORITY) {
            throw new IllegalArgumentException(String.format("Priority", priority,
                    Thread.MIN_PRIORITY));
        }

        if (priority > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException(String.format("Priority", priority,
                    Thread.MAX_PRIORITY));
        }
        this.priority = priority;
        return this;
    }

    public ThreadFactory build() {
        return build(this);
    }

    private ThreadFactory build(ThreadFactoryBuilder builder) {
        final String prefix = builder.namePrefix;
        final Boolean isDaemon = builder.daemon;
        final Integer priority = builder.priority;

        final AtomicLong count = new AtomicLong(0);

        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable);
                if (isDaemon != null) {
                    thread.setDaemon(isDaemon);
                }
                if (priority != null) {
                    thread.setPriority(priority);
                }
                if (prefix != null) {
                    thread.setName(prefix + "-" + count.getAndIncrement());
                }
                thread.setUncaughtExceptionHandler((Thread thread1, Throwable throwable) -> {
                    log.error("Exception occurred in thread factory for {} : due to ", thread1.getName(),
                            throwable.getStackTrace());
                });
                return thread;
            }
        };
    }
}
