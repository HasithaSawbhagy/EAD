package com.SpringbootTest.orderservice.controller;

import com.SpringbootTest.orderservice.dto.OrderItemRequest;
import com.SpringbootTest.orderservice.dto.OrderItemResponse;
import com.SpringbootTest.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItem")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderItem(@RequestBody OrderItemRequest orderItemRequest){
        orderService.createOrderItem(orderItemRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderItemResponse> getAllOrderItem(){
        return orderService.getAllOrderItem();
    }
}
