package com.SpringbootTest.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderRequest {
    private String user_id;
    private String order_date;
    private String status;
    private String delivery_address;
}
