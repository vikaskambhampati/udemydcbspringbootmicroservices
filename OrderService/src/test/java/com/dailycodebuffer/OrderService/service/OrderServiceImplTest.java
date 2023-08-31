package com.dailycodebuffer.OrderService.service;

import com.dailycodebuffer.OrderService.entity.OrderEntity;
import com.dailycodebuffer.OrderService.external.client.PaymentService;
import com.dailycodebuffer.OrderService.external.client.ProductService;
import com.dailycodebuffer.OrderService.external.client.request.PaymentRequest;
import com.dailycodebuffer.OrderService.external.client.response.PaymentResponse;
import com.dailycodebuffer.OrderService.external.client.response.ProductResponse;
import com.dailycodebuffer.OrderService.model.OrderRequest;
import com.dailycodebuffer.OrderService.model.OrderResponse;
import com.dailycodebuffer.OrderService.model.PaymentMode;
import com.dailycodebuffer.OrderService.repository.OrderRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Optional;


@SpringBootTest
public class OrderServiceImplTest {
//    @Mock
//    private OrderRepo orRepo;
//    @Mock
//    private ProductService prSer;
//    @Mock
//    private PaymentService paySer;
//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    OrderService orderService = new OrderServiceImpl();
//
//    @DisplayName("Success Scenario of Get Order Details Method")
//    @Test
//    void test_when_GetOrderDetails_Success(){
//        //Mocking
//        OrderEntity orderEntity = getOrderEntity();
//        Mockito.when(orRepo.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(orderEntity));
//
//        ProductResponse productResponse = getProductResponse();
//        Mockito.when(restTemplate.getForObject("http://Product-Service/product/getProductById/" + orderEntity.getProduct_id(), ProductResponse.class))
//                .thenReturn(productResponse);
//
//        PaymentResponse paymentResponse = getPaymentResponse();
//        Mockito.when(restTemplate.getForObject("http://Payment-Service/payment/getPaymentDetailsByOrderId/" + orderEntity.getOrder_id(), PaymentResponse.class))
//                .thenReturn(paymentResponse);
//
//        //Actual
//        OrderResponse orderResponse = orderService.getOrderDetailsById(1);
//
//        //Verification
//        Mockito.verify(orRepo, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
//        Mockito.verify(restTemplate, Mockito.times(1)).getForObject("http://Product-Service/product/getProductById/" + orderEntity.getProduct_id(), ProductResponse.class);
//        Mockito.verify(restTemplate, Mockito.times(1)).getForObject("http://Payment-Service/payment/getPaymentDetailsByOrderId/" + orderEntity.getOrder_id(), PaymentResponse.class);
//
//        //Assert
//        Assertions.assertNotNull(orderResponse);
//        Assertions.assertEquals(orderEntity.getOrder_id(), orderResponse.getOrder_id());
//    }
//
//    private OrderEntity getOrderEntity() {
//        OrderEntity orderEntity = OrderEntity.builder()
//                .order_status("PLACED")
//                .order_quantity(10)
//                .order_date(Instant.now())
//                .order_amount(1000)
//                .product_id(1)
//                .order_id(1)
//                .build();
//        return orderEntity;
//    }
//
//    private ProductResponse getProductResponse() {
//        ProductResponse productResponse = ProductResponse.builder()
//                .product_id(1)
//                .product_name("trash")
//                .product_price(100)
//                .product_quantity(12)
//                .build();
//
//        return productResponse;
//    }
//
//    private PaymentResponse getPaymentResponse() {
//        PaymentResponse paymentResponse = PaymentResponse.builder()
//                .paymentId(1)
//                .orderId(1)
//                .paymentDate(Instant.now())
//                .PaymentMode("CASH")
//                .paymentStatus("SUCCESS")
//                .amount(1000)
//                .referenceNumber("asdwewewe")
//                .build();
//        return paymentResponse;
//    }
//
//    @DisplayName("Get Order Details by Id Failure")
//    @Test
//    void test_when_getOrderDetailsById_Fail(){
//
//        Mockito.when(orRepo.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(null));
//
//        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class,
//                () -> orderService.getOrderDetailsById(1));
//        Assertions.assertEquals("Order Details not Found", runtimeException.getMessage());
//
//        Mockito.verify(orRepo, Mockito.times(1)).findById(ArgumentMatchers.anyLong());
//
//    }
//
//    @DisplayName("Test when Save Order is Success")
//    @Test
//    void test_when_save_order_success(){
//        //Mocking
//        Mockito.when(prSer.reduceQuantity(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
//                .thenReturn(new ResponseEntity<Void>(HttpStatus.OK));
//
//        OrderEntity orderEntityTwo = getOrderEntityTwo();
//        Mockito.when(orRepo.save(orderEntityTwo))
//                .thenReturn(orderEntityTwo);
//
//        PaymentRequest paymentRequest = getPaymentRequest();
//        Mockito.when(paySer.savePayment(ArgumentMatchers.any(PaymentRequest.class)))
//                .thenReturn(new ResponseEntity<Long>(1L, HttpStatus.OK));
//
//        //Actual
//        OrderRequest orderRequest = getOrderRequest();
//        long orderId = orderService.saveOrder(orderRequest);
//
//        //Verify
//        Mockito.verify(prSer, Mockito.times(1)).reduceQuantity(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong());
//        Mockito.verify(orRepo, Mockito.times(2)).save(ArgumentMatchers.any());
//        Mockito.verify(paySer, Mockito.times(1)).savePayment(ArgumentMatchers.any(PaymentRequest.class));
//
//        //Assert
//        Assertions.assertEquals(0, orderId);
//    }
//
//    private OrderEntity getOrderEntityTwo() {
//        OrderEntity orderEntity = OrderEntity.builder()
//                .order_id(1)
//                .order_status("SUCCESS")
//                .order_quantity(10)
//                .order_amount(1000)
//                .order_date(Instant.now())
//                .product_id(1)
//                .build();
//        return orderEntity;
//    }
//
//    private PaymentRequest getPaymentRequest() {
//        PaymentRequest paymentRequest = PaymentRequest.builder()
//                .paymentMode(PaymentMode.CASH)
//                .referenceNumber("asdddd")
//                .amount(123)
//                .orderId(1)
//                .build();
//        return paymentRequest;
//    }
//
//    private OrderRequest getOrderRequest() {
//        OrderRequest orderRequest = OrderRequest.builder()
//                .order_amount(123)
//                .order_quantity(12)
//                .paymentMode(PaymentMode.CASH)
//                .product_id(1)
//                .build();
//        return orderRequest;
//    }


}