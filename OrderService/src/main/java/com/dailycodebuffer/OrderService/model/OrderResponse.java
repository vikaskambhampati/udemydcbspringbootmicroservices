package com.dailycodebuffer.OrderService.model;

import com.dailycodebuffer.OrderService.external.client.response.PaymentResponse;
import com.dailycodebuffer.OrderService.external.client.response.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    private long order_id;
    private long product_id;
    private long order_quantity;
    private Instant order_date;
    private String order_status;
    private long order_amount;
    private ProductResponse productResponse;
    private PaymentResponse paymentResponse;
}
