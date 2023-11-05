package com.springbootExample1.productservice.repository;

import com.springbootExample1.productservice.model.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryRepository extends MongoRepository <Delivery,String>{

}
