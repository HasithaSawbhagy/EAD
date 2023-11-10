package com.SpringbootTest.orderservice.service;

import com.SpringbootTest.orderservice.dto.OrderItemRequest;
import com.SpringbootTest.orderservice.dto.OrderItemResponse;
import com.SpringbootTest.orderservice.dto.ItemQuantity;
import com.SpringbootTest.orderservice.model.OrderItem;
import com.SpringbootTest.orderservice.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final WebClient webClient;

    //Create Order Item
    public void createOrderItem(OrderItemRequest orderItemRequest) {

        //Calculate Sub Price of Each Item According to Quantity
        int quantity = orderItemRequest.getQuantity(); // Get quantity
        BigDecimal price = orderItemRequest.getPrice(); //Get price
        BigDecimal quantityBigDecimal = BigDecimal.valueOf(quantity); //Convert Quantity into BigDecimal
        BigDecimal subprice = quantityBigDecimal.multiply(price); //Calculate Subprice
        String productId = orderItemRequest.getProduct_id(); // Store product ID in a variable


        try {
            OrderItem orderItem = OrderItem.builder()
                    .order_id(orderItemRequest.getOrder_id())
                    .product_id(orderItemRequest.getProduct_id())
                    .quantity(orderItemRequest.getQuantity())
                    .price(orderItemRequest.getPrice())
                    .subprice(subprice)
                    .build();

            //call inventory service and place order if product is available in stock
            Boolean result = webClient.get()
                    .uri("http://localhost:8081/api/product/isInStock?id={id}&quantity={quantity}", productId, quantity)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            if (Boolean.TRUE.equals(result)) {
                orderItemRepository.save(orderItem);
                log.info("Order Item {} is saved", orderItem.getId());
            } else {
                log.info("Product is out of stock. Please try again later");
            }

        } catch (Exception e) {
            log.error("Failed to create order item: " + e.getMessage());
            throw new RuntimeException("Failed to create order item");
        }
    }

    //Get All Order Items
    public List<OrderItemResponse> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();

        return orderItems.stream().map(this::mapToOrderItemResponse).toList();
    }

    //Get All Order Item by Order
    public List<OrderItemResponse> getOrderItemsByOrderId(String order_id) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(order_id);

        return orderItems.stream().map(this::mapToOrderItemResponse).toList();
    }

    private OrderItemResponse mapToOrderItemResponse(OrderItem orderItem) {
        return OrderItemResponse.builder()
                .id(orderItem.getId())
                .order_id(orderItem.getOrder_id())
                .product_id(orderItem.getProduct_id())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .subprice(orderItem.getSubprice())
                .build();
    }

    //Update Item Quantity
    public void updateItemQuantity(String Id, ItemQuantity itemQuantity) {
        OrderItem orderItem = orderItemRepository.findById(Id)
                .orElseThrow(() -> new NoSuchElementException("Order Item not found with ID: " + Id));

        // Get the current price and quantity
        BigDecimal price = orderItem.getPrice();
        int newQuantity = itemQuantity.getQuantity();

        // Update the quantity of the order item
        orderItem.setQuantity(newQuantity);
        orderItemRepository.save(orderItem);

        // Recalculate the subprice based on the updated quantity
        BigDecimal quantityBigDecimal = BigDecimal.valueOf(newQuantity);
        BigDecimal subprice = quantityBigDecimal.multiply(price);
        orderItem.setSubprice(subprice);
        orderItemRepository.save(orderItem);

        log.info("Order Item Quantity updated for ID {}: {}", Id, newQuantity);
        log.info("Subprice updated for ID {} to: {}", Id, subprice);
    }

    //Delete Order Item
    public void deleteOrderItem(String Id) {
        OrderItem orderItem = orderItemRepository.findById(Id)
                .orElseThrow(() -> new NoSuchElementException("Order not found with ID: " + Id));
        orderItemRepository.deleteById(Id);
        log.info("Order Item with ID {} is deleted successfully.", Id);
    }
}
