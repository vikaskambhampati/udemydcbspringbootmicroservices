package com.dailycodebuffer.PaymentService.controller;

import com.dailycodebuffer.PaymentService.model.PaymentRequest;
import com.dailycodebuffer.PaymentService.model.PaymentResponse;
import com.dailycodebuffer.PaymentService.repository.PaymentRepo;
import com.dailycodebuffer.PaymentService.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
    @Autowired
    private PaymentService paySer;

    @PostMapping(value = "/savePayment")
    public ResponseEntity<Long> savePayment(@RequestBody PaymentRequest payReq){
        long paymentId = paySer.savePayment(payReq);
        return new ResponseEntity<>(paymentId, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getPaymentDetailsByOrderId/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsByOrderId(@PathVariable long orderId){
        PaymentResponse payRes = paySer.getPaymentDetailsByOrderId(orderId);
        return new ResponseEntity<>(payRes, HttpStatus.FOUND);
    }
}
