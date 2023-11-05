package com.springbootExample1.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryResponse {
    private String id;
    private String order_id;
    private String delPerson_id;
    private String delPerson_name;
    private String status;
}