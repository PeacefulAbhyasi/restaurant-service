package com.interview.delivery.application;

import com.interview.delivery.config.ApplicationContextConfig;
import com.interview.delivery.consumer.OrderConsumer;
import com.interview.delivery.repository.DeliveryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan("com.interview")
public class App {

    public static void main(String[] args) {
       ApplicationContext context = SpringApplication.run(App.class, args);
       ApplicationContextConfig.getInstance().setApplicationContext(context);
       context.getBean(DeliveryRepository.class).initialize();
       context.getBean(OrderConsumer.class).initialize();
    }
}
