package com.SpringbootTest.orderservice.service;

import com.SpringbootTest.orderservice.dto.OrderRequest;
import com.SpringbootTest.orderservice.dto.OrderResponse;
import com.SpringbootTest.orderservice.model.Order;
import com.SpringbootTest.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    public void createOrder(OrderRequest orderRequest){
        try {
            Order order = Order.builder()
                    .user_id(orderRequest.getUser_id())
                    .order_date(orderRequest.getOrder_date())
                    .status(orderRequest.getStatus())
                    .delivery_address(orderRequest.getDelivery_address())
                    .build();

            orderRepository.save(order);
            log.info("Order {} created successfully", order.getId());

        } catch (Exception e) {
            log.error("Failed to create order: " + e.getMessage());
            throw new RuntimeException("Failed to create order");
        }
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(this::mapToOrderResponse).toList();
    }

    private OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .user_id(order.getUser_id())
                .order_date(order.getOrder_date())
                .status(order.getStatus())
                .delivery_address(order.getDelivery_address())
                .build();
    }

    public void deleteOrder(String Id) {
        Order order = orderRepository.findById(Id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + Id));
        orderRepository.deleteById(Id);
        log.info("Order with ID {} is deleted successfully.", Id);
    }
}
