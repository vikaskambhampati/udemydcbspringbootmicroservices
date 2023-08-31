package com.dailycodebuffer.OrderService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.Instant;

@Entity
@Table(name = "Order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;
    private long product_id;
    private long order_quantity;
    private Instant order_date;
    private String order_status;

    @Column(name = "Total_Order_Value")
    private long order_amount;
}
