package com.springbootExample1.productservice.controller;

import com.springbootExample1.productservice.dto.DeliveryRequest;
import com.springbootExample1.productservice.dto.DeliveryResponse;
import com.springbootExample1.productservice.dto.DeliveryUpdate;
import com.springbootExample1.productservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/delivery") 
@RequiredArgsConstructor
public class DeliveryContoller {
    private final DeliveryService deliveryService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public void createDelivery(@RequestBody DeliveryRequest deliveryRequest){
     deliveryService.createDelivery(deliveryRequest);

    }

    @PostMapping("/create/{orderId}")
    public void createDeliveryForOrder(
            @PathVariable String orderId,
            @RequestParam(name = "deliveryAddress") String deliveryAddress,
            @RequestParam(name = "contact") Long contact,
            @RequestParam(name = "totalCost") BigDecimal totalCost) {
        // Create the delivery with the provided delivery address
        deliveryService.createDeliveryForOrder(orderId, deliveryAddress, contact, totalCost);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DeliveryResponse>getAllDelivery(){
        return deliveryService.getAllDelivery();
    }

    @PutMapping("/{id}/assign")
    @ResponseStatus(HttpStatus.OK)
    public void assignDelivery(@PathVariable String id) {
        deliveryService.assignDelivery(id);
    }

    @PutMapping("/{id}/complete")
    @ResponseStatus(HttpStatus.OK)
    public void completeDelivery(@PathVariable String id) {
        deliveryService.completeDelivery(id);
    }

    @PutMapping("/{id}/assignDeliveryPerson")
    @ResponseStatus(HttpStatus.OK)
    public void updateDeliveryPersonInfo(@PathVariable String id, @RequestBody DeliveryUpdate deliveryUpdate) {
        deliveryService.updateDeliveryPersonInfo(id, deliveryUpdate);
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DeliveryResponse getDeliveryById(@PathVariable String id) {
        return deliveryService.getDeliveryById(id);
   }

}






