package com.dailycodebuffer.APIGateway.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
    @GetMapping(value = "/OrderFallBack")
    public String orderFallBack()
    {
        return "Order Service Down";
    }
    @GetMapping(value = "/ProductFallBack")
    public String productFallBack()
    {
        return "Product Service Down";
    }
    @GetMapping(value = "/PaymentFallBack")
    public String paymentFallBack()
    {
        return "Payment Service Down";
    }
}
