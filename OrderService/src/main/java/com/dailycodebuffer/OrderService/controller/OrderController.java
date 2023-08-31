package com.dailycodebuffer.OrderService.controller;

import com.dailycodebuffer.OrderService.model.OrderRequest;
import com.dailycodebuffer.OrderService.model.OrderResponse;
import com.dailycodebuffer.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orSer;

    @PreAuthorize("hasAuthority('Customer')")
    @PostMapping(value = "/saveOrder")
    public ResponseEntity<Long> saveOrder(@RequestBody OrderRequest orReq){
        long orderId = orSer.saveOrder(orReq);
        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('admins') || hasAuthority('customers')")
    @GetMapping(value = "/getOrderDetails/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable long orderId){
        OrderResponse orResp = orSer.getOrderDetailsById(orderId);
        return new ResponseEntity<>(orResp, HttpStatus.FOUND);
    }
}
