package com.SpringbootTest.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "orderitem")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderItem {
    @Id
    private String id;
    private String order_id;
    private String product_id;
    private int quantity;
    private BigDecimal price;

}
