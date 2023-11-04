package com.SpringbootTest.inventoryservice.service;

import com.SpringbootTest.inventoryservice.Exception.NotFoundException;
import com.SpringbootTest.inventoryservice.dto.ProductRequest;
import com.SpringbootTest.inventoryservice.dto.ProductResponse;
import com.SpringbootTest.inventoryservice.model.Product;
import com.SpringbootTest.inventoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public void createProduct(ProductRequest productRequest){
        Product product= Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product>  products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public Product updateProduct(String id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }
        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }
        if (product.getQuantity() != null) {
            existingProduct.setQuantity(product.getQuantity());
        }

        return productRepository.save(existingProduct);
    }


}
