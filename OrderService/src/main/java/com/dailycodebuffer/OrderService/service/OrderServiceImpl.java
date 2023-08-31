package com.dailycodebuffer.OrderService.service;

import com.dailycodebuffer.OrderService.entity.OrderEntity;
import com.dailycodebuffer.OrderService.external.client.PaymentService;
import com.dailycodebuffer.OrderService.external.client.ProductService;
import com.dailycodebuffer.OrderService.external.client.request.PaymentRequest;
import com.dailycodebuffer.OrderService.external.client.response.PaymentResponse;
import com.dailycodebuffer.OrderService.external.client.response.ProductResponse;
import com.dailycodebuffer.OrderService.model.OrderRequest;
import com.dailycodebuffer.OrderService.model.OrderResponse;
import com.dailycodebuffer.OrderService.repository.OrderRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepo orRepo;
    @Autowired
    private ProductService prSer;
    @Autowired
    private PaymentService paySer;
    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.product}")
    private String productServiceUrl;
    @Value("${microservices.payment}")
    private String paymentServiceUrl;

    @Override
    public long saveOrder(OrderRequest orReq) {
        log.info("Saving an Order");

        prSer.reduceQuantity(orReq.getProduct_id(), orReq.getOrder_quantity());

        OrderEntity orEntity = OrderEntity.builder()
                .product_id(orReq.getProduct_id())
                .order_amount(orReq.getOrder_amount())
                .order_date(Instant.now())
                .order_quantity(orReq.getOrder_quantity())
                .order_status("Created")
                .build();
        orRepo.save(orEntity);

        PaymentRequest payReq = PaymentRequest.builder()
                .orderId(orEntity.getOrder_id())
                .amount(orReq.getOrder_amount())
                .paymentMode(orReq.getPaymentMode())
                .build();

        paySer.savePayment(payReq);
        String order_status = "PLACED";

        orEntity.setOrder_status(order_status);

        orRepo.save(orEntity);

        log.info("Order Completed");

        return orEntity.getOrder_id();
    }

    @Override
    public OrderResponse getOrderDetailsById(long orderId) {
        OrderEntity orEnt = orRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order Details not Found"));

        ProductResponse pResp;

        PaymentResponse payRes;

        pResp = restTemplate.getForObject(productServiceUrl + "getProductById/" + orEnt.getProduct_id(), ProductResponse.class);

        payRes = restTemplate.getForObject(paymentServiceUrl + "getPaymentDetailsByOrderId/" + orEnt.getOrder_id(), PaymentResponse.class);

        OrderResponse orResp = new OrderResponse();
        BeanUtils.copyProperties(orEnt, orResp);

        orResp.setProductResponse(pResp);
        orResp.setPaymentResponse(payRes);

        return orResp;
    }
}
