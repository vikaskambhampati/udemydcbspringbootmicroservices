package com.dailycodebuffer.PaymentService.service;

import com.dailycodebuffer.PaymentService.entity.PaymentEntity;
import com.dailycodebuffer.PaymentService.model.PaymentRequest;
import com.dailycodebuffer.PaymentService.model.PaymentResponse;
import com.dailycodebuffer.PaymentService.repository.PaymentRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepo payRepo;
    @Override
    public long savePayment(PaymentRequest payReq) {
        log.info("Inside Save Payment");
        PaymentEntity payEnt = PaymentEntity.builder()
                .orderId(payReq.getOrderId())
                .PaymentMode(String.valueOf(payReq.getPaymentMode()))
                .referenceNumber(payReq.getReferenceNumber())
                .paymentDate(Instant.now())
                .paymentStatus("Success")
                .amount(payReq.getAmount())
                .build();
        payRepo.save(payEnt);
        log.info("Payment Details Saved");
        return payEnt.getPaymentId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        PaymentEntity payEnt = payRepo.findByOrderId(orderId);
        PaymentResponse payRes = new PaymentResponse();
        BeanUtils.copyProperties(payEnt, payRes);
        return payRes;
    }
}
