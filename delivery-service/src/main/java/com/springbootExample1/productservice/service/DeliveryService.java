package com.springbootExample1.productservice.service;

import com.springbootExample1.productservice.dto.DeliveryRequest;
import com.springbootExample1.productservice.dto.DeliveryResponse;
import com.springbootExample1.productservice.dto.DeliveryUpdate;
import com.springbootExample1.productservice.model.Delivery;
import com.springbootExample1.productservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final WebClient webClient;

    public void createDelivery(DeliveryRequest deliveryRequest){
        Delivery delivery= Delivery.builder()
                .order_id(deliveryRequest.getOrder_id())
                .delPerson_id(deliveryRequest.getDelPerson_id())
                .delPerson_name(deliveryRequest.getDelPerson_name())
                .status(deliveryRequest.getStatus())
                .build();
        deliveryRepository.save(delivery);
        log.info("Delivery {} is saved",delivery.getId());

    }

    public void createDeliveryFoOrder(String orderId) {
        Delivery delivery = Delivery.builder()
                .order_id(orderId)
                // Set other fields to default or null values
                .delPerson_id("Not Assign") // Example: Set to null
                .delPerson_name("Not Assign") // Example: Set to null
                .status("Not Assign") // Set to a default value
                .build();

        deliveryRepository.save(delivery);
        log.info("Delivery {} is saved", delivery.getId());
    }

    public List<DeliveryResponse> getAllDelivery() {
        List<Delivery> deliveries=deliveryRepository.findAll();
        return deliveries.stream().map(this::mapToDeliveryResponse).toList();
    }
    private DeliveryResponse mapToDeliveryResponse (Delivery delivery){
        return DeliveryResponse.builder()
                .id(delivery.getId())
                .order_id(delivery.getOrder_id())
                .delPerson_id(delivery.getDelPerson_id())
                .delPerson_name(delivery.getDelPerson_name())
                .status(delivery.getStatus())
                .build();
    }

     public void assignDelivery(String id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setStatus("on the way");
        deliveryRepository.save(delivery);
        log.info("Delivery {} is assigned to a delivery person.", id);
    }

    public void completeDelivery(String id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setStatus("completed");
        deliveryRepository.save(delivery);
        log.info("Delivery {} is completed.", id);
    }
    public void updateDeliveryPersonInfo(String id, DeliveryUpdate deliveryUpdate) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setDelPerson_id(deliveryUpdate.getDelPerson_id());
        delivery.setDelPerson_name(deliveryUpdate.getDelPerson_name());
        delivery.setStatus(deliveryUpdate.getStatus());
        deliveryRepository.save(delivery);
        log.info("Delivery {} updated with delivery person info: ID={}, Name={}", id, deliveryUpdate.getDelPerson_id(), deliveryUpdate.getDelPerson_name());
    }
    public DeliveryResponse getDeliveryById(String id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        return mapToDeliveryResponse(delivery);
    }
}




  
    



