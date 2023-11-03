package com.SpringbootTest.inventoryservice.controller;


import com.SpringbootTest.inventoryservice.Exception.NotFoundException;
import com.SpringbootTest.inventoryservice.dto.ProductRequest;
import com.SpringbootTest.inventoryservice.dto.ProductResponse;
import com.SpringbootTest.inventoryservice.repository.ProductRepository;
import com.SpringbootTest.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    //Creat product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    //get all products
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    //delete user
    @DeleteMapping("delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(("Product not found with id" + id));
        }
        productRepository.deleteById(id);
        return "Product with id " + id + "has been deleted";
    }
}
