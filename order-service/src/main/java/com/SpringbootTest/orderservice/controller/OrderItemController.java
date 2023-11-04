package com.SpringbootTest.orderservice.controller;

import com.SpringbootTest.orderservice.dto.OrderAddressUpdate;
import com.SpringbootTest.orderservice.dto.OrderItemRequest;
import com.SpringbootTest.orderservice.dto.OrderItemResponse;
import com.SpringbootTest.orderservice.dto.ItemQuantity;
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

    //Create Order Item
    @PostMapping
    public ResponseEntity<String> createOrderItem(@RequestBody OrderItemRequest orderItemRequest){
        try {
            orderItemService.createOrderItem(orderItemRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order Item created successfully");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Order creation failed: " + e.getMessage());
        }
    }

    //Get All Order Items
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItemResponse> getAllOrderItems(){
        return orderItemService.getAllOrderItems();
    }

    //Update Item Quantity
    @PatchMapping("/{Id}/quantity")
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
