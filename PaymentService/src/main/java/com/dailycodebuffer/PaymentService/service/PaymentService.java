package com.dailycodebuffer.PaymentService.service;

import com.dailycodebuffer.PaymentService.model.PaymentRequest;
import com.dailycodebuffer.PaymentService.model.PaymentResponse;

public interface PaymentService {
    long savePayment(PaymentRequest payReq);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
