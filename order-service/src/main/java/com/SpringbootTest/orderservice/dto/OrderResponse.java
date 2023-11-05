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
public class OrderResponse {
    private String id;
    private String user_id;
    private String order_date;
    private String status;
    private String delivery_address;
    private BigDecimal totalCost;
}
