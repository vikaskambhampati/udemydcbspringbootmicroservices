package com.dailycodebuffer.OrderService.external.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "externalOrder", fallbackMethod = "productFallBack")
@FeignClient(name = "product", url = "${microservices.product}")
public interface ProductService {
    @PutMapping(value = "/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity);

    default void productFallBack(){
        throw new RuntimeException("Product FallBack Method");
    }
}
