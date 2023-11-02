package com.SpringbootTest.orderservice.repository;

import com.SpringbootTest.orderservice.model.order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<order, String> {
}
