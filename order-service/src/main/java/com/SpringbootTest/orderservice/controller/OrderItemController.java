package com.SpringbootTest.orderservice.controller;

import com.SpringbootTest.orderservice.dto.OrderItemRequest;
import com.SpringbootTest.orderservice.dto.OrderItemResponse;
import com.SpringbootTest.orderservice.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/orderItem")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderItem(@RequestBody OrderItemRequest orderItemRequest){
        orderItemService.createOrderItem(orderItemRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItemResponse> getAllOrderItems(){
        return orderItemService.getAllOrderItems();
    }

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
