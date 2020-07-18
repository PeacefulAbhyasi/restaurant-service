package com.interview.restaurant.application;

import com.interview.restaurant.config.ApplicationContextConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan("com.interview")
public class App {

    public static void main(String[] args) {
        ApplicationContextConfig.getInstance().setApplicationContext(SpringApplication.run(App.class, args));
    }
}
