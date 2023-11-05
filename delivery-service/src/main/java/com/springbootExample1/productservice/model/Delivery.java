package com.springbootExample1.productservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "delivery")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Delivery {
    @Id
    private String id;
    private String order_id;
    private String delPerson_id;
    private String delPerson_name;
    private String status = "pending";

}

