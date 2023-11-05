package com.springbootExample1.productservice.service;

import com.springbootExample1.productservice.dto.DeliveryRequest;
import com.springbootExample1.productservice.dto.DeliveryResponse;
import com.springbootExample1.productservice.model.Delivery;
import com.springbootExample1.productservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

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
}



// package com.springbootExample1.productservice.service;

// import com.springbootExample1.productservice.dto.DeliveryRequest;
// import com.springbootExample1.productservice.dto.DeliveryResponse;
// import com.springbootExample1.productservice.model.Delivery;
// import com.springbootExample1.productservice.repository.DeliveryRepository;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// import javax.management.RuntimeErrorException;

// @Service
// @RequiredArgsConstructor
// @Slf4j
// public class DeliveryService {

//     private final DeliveryRepository deliveryRepository;

//     public void createDelivery(DeliveryRequest deliveryRequest){
//         try{
//             Delivery delivery= Delivery.builder()
//                 .order_id(deliveryRequest.getOrder_id())
//                 .delPerson_id(deliveryRequest.getDelPerson_id())
//                 .delPerson_name(deliveryRequest.getDelPerson_name())
//                 // .status(deliveryRequest.getStatus())
//                 .build();
//         deliveryRepository.save(delivery);
//         log.info("Delivery {} is saved",delivery.getId());

//     } catch(Exception e){
//         log.error("Failed to create delivery: "+ e.getMessage());
//         throw new RuntimeErrorException(null, "Failed to create order.");
//     }

//     }
//     public List<DeliveryResponse> getAllDelivery() {
//         List<Delivery> deliveries=deliveryRepository.findAll();
//         return deliveries.stream().map(this::mapToDeliveryResponse).toList();
//     }
//     // get by delivery id
//     // public List<DeliveryResponse> getDeliveryById(String id){
//     //     List<Delivery> deliveryById=deliveryRepository.findById(id).get();
//     //     return deliveryById.stream().map(this::mapToDeliveryResponse).toList();
//     // }
//     private DeliveryResponse mapToDeliveryResponse (Delivery delivery){
//         return DeliveryResponse.builder()
//                 .id(delivery.getId())
//                 .order_id(delivery.getOrder_id())
//                 .delPerson_id(delivery.getDelPerson_id())
//                 .delPerson_name(delivery.getDelPerson_name())
//                 .status("pending") 
//                 .build();
//     }


//     public void updateDeliveryStatus(String deliveryId, String newStatus) {
//     Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
//     if (optionalDelivery.isPresent()) {
//         Delivery delivery = optionalDelivery.get();
//         // delivery.setStatus(newStatus);
//         deliveryRepository.save(delivery);
//         log.info("Delivery {} status updated to: {}", delivery.getId(), newStatus);
//     } else {
//         log.error("Delivery with ID {} not found.", deliveryId);
//         throw new RuntimeException("Delivery not found.");
//     }
//     }
//     public List<DeliveryResponse> ok(String string) {
//         return null;
//     }
// }


  
    



