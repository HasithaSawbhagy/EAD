package com.SpringbootTest.orderservice.controller;

import com.SpringbootTest.orderservice.dto.OrderRequest;
import com.SpringbootTest.orderservice.dto.OrderResponse;
import com.SpringbootTest.orderservice.dto.OrderStatusUpdate;
import com.SpringbootTest.orderservice.dto.OrderAddressUpdate;
import com.SpringbootTest.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            orderService.createOrder(orderRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order creation failed: " + e.getMessage());
        }
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrdersByUserId(@PathVariable String user_id) {
        return orderService.getOrdersByUserId(user_id);
    }

    @PatchMapping("/{Id}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable String Id, @RequestBody OrderStatusUpdate orderStatusUpdate) {
        try {
            orderService.updateOrderStatus(Id, orderStatusUpdate);
            return ResponseEntity.status(HttpStatus.OK).body("Order status updated successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found with ID: " + Id);
        }
    }
    @PatchMapping("/{Id}/address")
    public ResponseEntity<String> updateOrderAddress(@PathVariable String Id, @RequestBody OrderAddressUpdate orderAddressUpdate) {
        try {
            orderService.updateOrderAddress(Id, orderAddressUpdate);
            return ResponseEntity.status(HttpStatus.OK).body("Order address updated successfully.");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found with ID: " + Id);
        }
    }

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
