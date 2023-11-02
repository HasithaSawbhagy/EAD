package com.SpringbootTest.orderservice.service;

import com.SpringbootTest.orderservice.dto.OrderRequest;
import com.SpringbootTest.orderservice.model.Order;
import com.SpringbootTest.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    public void createOrder(OrderRequest orderRequest){
        Order order = Order.builder()
                .user_id(orderRequest.getUser_id())
                .order_date(orderRequest.getOrder_date())
                .status(orderRequest.getStatus())
                .delivery_address(orderRequest.getDelivery_address())
                .build();

        orderRepository.save(order);
        log.info("Order {} is saved", order.getId());
    }
}
