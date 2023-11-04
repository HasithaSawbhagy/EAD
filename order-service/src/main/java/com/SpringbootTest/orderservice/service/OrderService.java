package com.SpringbootTest.orderservice.service;

import com.SpringbootTest.orderservice.dto.OrderRequest;
import com.SpringbootTest.orderservice.dto.OrderResponse;
import com.SpringbootTest.orderservice.dto.OrderStatusUpdate;
import com.SpringbootTest.orderservice.dto.OrderAddressUpdate;
import com.SpringbootTest.orderservice.model.Order;
import com.SpringbootTest.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    public void createOrder(OrderRequest orderRequest){
        try {
            String todayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Order order = Order.builder()
                    .user_id(orderRequest.getUser_id())
                    .order_date(todayDate)
                    .status("pending")
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
    public List<OrderResponse> getOrdersByUserId(String user_id) {
        List<Order> orders = orderRepository.findByUserId(user_id);

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

    public void updateOrderStatus(String Id, OrderStatusUpdate orderStatusUpdate) {

        Order order = orderRepository.findById(Id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + Id));

        order.setStatus(orderStatusUpdate.getStatus());

        orderRepository.save(order);

        log.info("Order status updated for ID {}: {}", Id, orderStatusUpdate.getStatus());

    }

    public void updateOrderAddress(String Id, OrderAddressUpdate orderAddressUpdate) {

        Order order = orderRepository.findById(Id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + Id));

        order.setDelivery_address(orderAddressUpdate.getDelivery_address());

        orderRepository.save(order);

        log.info("Order address updated for ID {}: {}", Id, orderAddressUpdate.getDelivery_address());

    }

    public void deleteOrder(String Id) {
        Order order = orderRepository.findById(Id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + Id));
        orderRepository.deleteById(Id);
        log.info("Order with ID {} is deleted successfully.", Id);
    }
}
