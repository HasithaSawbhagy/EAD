package com.SpringbootTest.orderservice.repository;

import com.SpringbootTest.orderservice.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderItemRepository extends MongoRepository<OrderItem, String>{
    @Query("{ 'order_id' : ?0 }")
    List<OrderItem> findByOrderId(String order_id);
}
