package com.SpringbootTest.inventoryservice.repository;

import com.SpringbootTest.inventoryservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
