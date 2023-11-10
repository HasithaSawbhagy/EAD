package com.SpringbootTest.orderservice.controller;

import com.SpringbootTest.orderservice.dto.*;
import com.SpringbootTest.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    //Create Order
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            orderService.createOrder(orderRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order creation failed: " + e.getMessage());
        }
    }


    //Get All Order
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders();
    }

    //Get All Order by User
    @GetMapping("/user/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrdersByUserId(@PathVariable String user_id) {
        return orderService.getOrdersByUserId(user_id);
    }

    //Update Order Status
    @PatchMapping("/status/{Id}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable String Id, @RequestBody OrderStatusUpdate orderStatusUpdate) {
        try {
            orderService.updateOrderStatus(Id, orderStatusUpdate);
            return ResponseEntity.status(HttpStatus.OK).body("Order status updated successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found with ID: " + Id);
        }
    }

    //Update Delivery Address
    @PatchMapping("/address/{Id}")
    public ResponseEntity<String> updateOrderAddress(@PathVariable String Id, @RequestBody OrderAddressUpdate orderAddressUpdate) {
        try {
            orderService.updateOrderAddress(Id, orderAddressUpdate);
            return ResponseEntity.status(HttpStatus.OK).body("Order address updated successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found with ID: " + Id);
        }
    }

    @PatchMapping("/updateTotalCost/{orderId}")
    public ResponseEntity<String> updateOrderTotalCost(@PathVariable String orderId) {
        try {
            orderService.updateOrderTotalCost(orderId);
            return ResponseEntity.status(HttpStatus.OK).body("Total cost updated for Order ID: " + orderId);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found with ID: " + orderId);
        }
    }

    //Delete Order
    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteOrder(@PathVariable String Id) {
        try {
            orderService.deleteOrder(Id);
            return ResponseEntity.status(HttpStatus.OK).body("Order with ID " + Id + " deleted successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found with ID: " + Id);
        }
    }
}
