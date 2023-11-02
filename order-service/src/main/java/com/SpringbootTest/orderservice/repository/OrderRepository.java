package com.SpringbootTest.orderservice.repository;

import com.SpringbootTest.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
