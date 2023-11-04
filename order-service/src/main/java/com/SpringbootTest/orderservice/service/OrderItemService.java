package com.SpringbootTest.orderservice.service;

import com.SpringbootTest.orderservice.dto.OrderItemRequest;
import com.SpringbootTest.orderservice.dto.OrderItemResponse;
import com.SpringbootTest.orderservice.dto.ItemQuantity;
import com.SpringbootTest.orderservice.model.OrderItem;
import com.SpringbootTest.orderservice.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public void createOrderItem(OrderItemRequest orderItemRequest){
        try {
            OrderItem orderItem = OrderItem.builder()
                    .order_id(orderItemRequest.getOrder_id())
                    .product_id(orderItemRequest.getProduct_id())
                    .quantity(orderItemRequest.getQuantity())
                    .price(orderItemRequest.getPrice())
                    .build();

            orderItemRepository.save(orderItem);
            log.info("Order Item {} is saved", orderItem.getId());
        }catch (Exception e){
            log.error("Failed to create order item: " + e.getMessage());
            throw new RuntimeException("Failed to create order item");
        }
    }

    public List<OrderItemResponse> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();

        return orderItems.stream().map(this::mapToOrderItemResponse).toList();
    }

    private OrderItemResponse mapToOrderItemResponse(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .order_id(orderItem.getOrder_id())
                .product_id(orderItem.getProduct_id())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }
    public void updateItemQuantity(String Id, ItemQuantity itemQuantity) {

        OrderItem orderItem = orderItemRepository.findById(Id)
                .orElseThrow(() -> new NoSuchElementException("Order Item not found with ID: " + Id));

        orderItem.setQuantity(itemQuantity.getQuantity());

        orderItemRepository.save(orderItem);

        log.info("Order Item Quantity updated for ID {}: {}", Id, itemQuantity.getQuantity());

    }

    public void deleteOrderItem(String Id) {
        OrderItem orderItem = orderItemRepository.findById(Id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + Id));
        orderItemRepository.deleteById(Id);
        log.info("Order Item with ID {} is deleted successfully.", Id);
    }
}
