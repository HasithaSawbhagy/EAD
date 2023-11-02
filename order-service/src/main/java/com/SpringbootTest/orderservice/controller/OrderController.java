package com.SpringbootTest.orderservice.controller;

import com.SpringbootTest.orderservice.dto.OrderRequest;
import com.SpringbootTest.orderservice.dto.OrderResponse;
import com.SpringbootTest.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getAllOrders(){
        return orderService.getAllOrders();
    }
}
