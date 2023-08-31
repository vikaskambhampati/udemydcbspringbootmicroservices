package com.dailycodebuffer.ProductService.controller;

import com.dailycodebuffer.ProductService.model.ProductRequest;
import com.dailycodebuffer.ProductService.model.ProductResponse;
import com.dailycodebuffer.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Status;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService prser;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping(value = "/addProduct")
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest p)
    {
        long product_id = prser.addProduct(p);
        return new ResponseEntity<>(product_id, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer') || hasAuthority('SCOPE_internal')")
    @GetMapping(value = "/getProductById/{product_id}")
    public ResponseEntity<ProductResponse> getProductDetailsById(@PathVariable long product_id)
    {
        ProductResponse pResp = prser.getProducyById(product_id);
        return new ResponseEntity<>(pResp, HttpStatus.FOUND);
    }

    @PutMapping(value = "/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity)
    {
        prser.reduceQuantity(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
