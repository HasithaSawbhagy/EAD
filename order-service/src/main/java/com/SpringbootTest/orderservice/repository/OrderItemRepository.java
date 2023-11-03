package com.SpringbootTest.orderservice.repository;

import com.SpringbootTest.orderservice.model.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderItemRepository extends MongoRepository<OrderItem, String>{
}
