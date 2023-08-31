package com.dailycodebuffer.OrderService.external.client;

import com.dailycodebuffer.OrderService.external.client.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name = "externalOrder", fallbackMethod = "paymentFallBack")
@FeignClient(name = "payment", url = "${microservices.payment}")
public interface PaymentService {
    @PostMapping(value = "/savePayment")
    ResponseEntity<Long> savePayment(@RequestBody PaymentRequest payReq);

    default void paymentFallBack(){
        throw new RuntimeException("Payment FallBack Method");
    }
}
