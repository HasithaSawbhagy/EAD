package com.SpringbootTest.orderservice.controller;

import com.SpringbootTest.orderservice.dto.*;
import com.SpringbootTest.orderservice.service.OrderItemService;
import com.SpringbootTest.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/orderItem")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;
    private final WebClient webClient;
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    //Create Order Item
    @PostMapping
    public ResponseEntity<String> createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {
        try {
            orderItemService.createOrderItem(orderItemRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order Item created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order creation failed: " + e.getMessage());
        }
    }

    //get product details from inventory service
    @GetMapping("/getAllProductsDetails")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProductsFromInventoryService() {
        try {
            List<ProductResponse> products = webClient.get()
                    .uri("http://localhost:8080/api/product") // Update the URI to match your actual endpoint
                    .retrieve()
                    .bodyToFlux(ProductResponse.class)
                    .collectList()
                    .block();

            log.info("Received products from InventoryService: {}", products);
            return products;
        } catch (Exception e) {
            log.error("Failed to get products from InventoryService: " + e.getMessage());
            throw new RuntimeException("Failed to get products from InventoryService" + e.getMessage());
        }
    }

    //Get All Order Items
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItemResponse> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    //Get All Order Item by Order Id
    @GetMapping("/order/{order_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItemResponse> getOrdersByUserId(@PathVariable String order_id) {
        return orderItemService.getOrderItemsByOrderId(order_id);
    }

    //Update Item Quantity
    @PatchMapping("/quantity/{Id}")
    public ResponseEntity<String> updateItemQuantity(@PathVariable String Id, @RequestBody ItemQuantity itemQuantity) {
        try {
            orderItemService.updateItemQuantity(Id, itemQuantity);
            return ResponseEntity.status(HttpStatus.OK).body("Order item quantity updated successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order item not found with ID: " + Id);
        }
    }

    //Delete Order Item
    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteOrderItem(@PathVariable String Id) {
        try {
            orderItemService.deleteOrderItem(Id);
            return ResponseEntity.status(HttpStatus.OK).body("Order Item with ID " + Id + " deleted successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order Item not found with ID: " + Id);
        }
    }
}
