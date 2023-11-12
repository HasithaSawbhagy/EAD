package com.SpringbootTest.orderservice.repository;

import com.SpringbootTest.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
public interface OrderRepository extends MongoRepository<Order, String> {
    @Query("{ 'user_id' : ?0 }")
    List<Order> findByUserId(String user_id);
}
