package com.dailycodebuffer.ProductService.service;

import com.dailycodebuffer.ProductService.entity.ProductEntity;
import com.dailycodebuffer.ProductService.model.ProductRequest;
import com.dailycodebuffer.ProductService.model.ProductResponse;
import com.dailycodebuffer.ProductService.repository.ProductRepo;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepo prepo;
    @Override
    public long addProduct(ProductRequest p) {
        log.info("adding product");
        ProductEntity pEntiy = ProductEntity.builder()
                .product_name(p.getProduct_name())
                .product_price(p.getProduct_price())
                .product_quantity(p.getProduct_quantity()).build();

        prepo.save(pEntiy);
        log.info("Product Saved");
        return pEntiy.getProduct_id();
    }

    @Override
    public ProductResponse getProducyById(long productId) {
        log.info("Get product by Id");
        ProductEntity pEntity = prepo.findById(productId).orElseThrow(() -> new RuntimeException("Product with Id not found"));
        ProductResponse pResp = ProductResponse.builder()
                .product_id(pEntity.getProduct_id())
                .product_name(pEntity.getProduct_name())
                .product_price(pEntity.getProduct_price())
                .product_quantity(pEntity.getProduct_quantity()).build();
        log.info("Product found");
        return pResp;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        ProductEntity pEntity = prepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        long existingQuantity = pEntity.getProduct_quantity();
        if(quantity <= existingQuantity)
            pEntity.setProduct_quantity(existingQuantity - quantity);
        prepo.save(pEntity);
        log.info("Quantity reduced of product {}", pEntity.getProduct_name());
    }
}
