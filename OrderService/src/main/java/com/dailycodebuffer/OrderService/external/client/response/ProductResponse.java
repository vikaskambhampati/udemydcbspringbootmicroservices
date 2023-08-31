package com.dailycodebuffer.OrderService.external.client.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private long product_id;
    private String product_name;
    private long product_price;
    private long product_quantity;
}
