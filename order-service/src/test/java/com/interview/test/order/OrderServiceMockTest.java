package com.interview.test.order;

import com.interview.restaurant.model.request.CreateOrderRequest;
import com.interview.restaurant.service.OrderService;
import com.interview.restaurant.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceMockTest {

    @InjectMocks
    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void createOrder() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setItemId("123456");
        request.setNumberOfItems("5");
        orderService.createOrder(request);
    }
}
