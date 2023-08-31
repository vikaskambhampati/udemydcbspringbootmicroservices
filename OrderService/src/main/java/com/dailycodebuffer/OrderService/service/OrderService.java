package com.dailycodebuffer.OrderService.service;

import com.dailycodebuffer.OrderService.model.OrderRequest;
import com.dailycodebuffer.OrderService.model.OrderResponse;

public interface OrderService {
    long saveOrder(OrderRequest orReq);

    OrderResponse getOrderDetailsById(long orderId);
}
