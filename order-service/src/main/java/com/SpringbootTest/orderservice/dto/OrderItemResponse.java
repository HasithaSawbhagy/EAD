package com.SpringbootTest.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private String id;
    private String order_id;
    private String product_id;
    private int quantity;
    private BigDecimal price;
    private BigDecimal subprice;
}
