package com.springbootExample1.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DeliveryUpdate {
    private String delPerson_id;
    private String delPerson_name;
    private String status;
}
