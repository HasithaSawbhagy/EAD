package com.SpringbootTest.inventoryservice.controller;


import com.SpringbootTest.inventoryservice.Exception.NotFoundException;
import com.SpringbootTest.inventoryservice.dto.ProductRequest;
import com.SpringbootTest.inventoryservice.dto.ProductResponse;
import com.SpringbootTest.inventoryservice.model.Product;
import com.SpringbootTest.inventoryservice.repository.ProductRepository;
import com.SpringbootTest.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    //get current product quantity by id
    @GetMapping("getQuantityId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> getProductQuantityById(@PathVariable String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product is not found with id:" + id));
        int quantity = Integer.parseInt(product.getQuantity());
        return ResponseEntity.ok(quantity);
    }

    //find whether the product is in stock or not
    @GetMapping("isInStock")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> isProductQuantityGreaterThanZero(@RequestParam String id, @RequestParam int quantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product is not found with id:" + id));
        int stockQuantity = Integer.parseInt(product.getQuantity());

        boolean isQuantityGreaterThanZero = stockQuantity > 0 && stockQuantity >= quantity;

        if (isQuantityGreaterThanZero) {
            // Update the stockQuantity after checking if the product is in stock
            int updatedStockQuantity = stockQuantity - quantity;
            product.setQuantity(String.valueOf(updatedStockQuantity));
            productRepository.save(product);
        }

        return ResponseEntity.ok(isQuantityGreaterThanZero);
    }



    //get all products
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable String id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product is not found with id:" + id));
    }

    //delete Product
    @DeleteMapping("delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(("Product not found with id" + id));
        }
        productRepository.deleteById(id);
        return "Product with id " + id + "has been deleted";
    }

    //Update Product
    @PutMapping("update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        Product updateProduct = productService.updateProduct(id, product);
        return ResponseEntity.ok(updateProduct);
    }

    //Update  Quantity after purchasing
    @PutMapping("updateQuantity/{id}")
    public ResponseEntity<Product> updateProductQuantity(@PathVariable String id, @RequestBody Product product) {
        Product updateProductQuantity = productService.updateProductQuantity(id, product);
        return ResponseEntity.ok(updateProductQuantity);
    }


}
