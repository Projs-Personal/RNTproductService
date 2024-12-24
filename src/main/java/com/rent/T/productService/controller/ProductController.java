package com.rent.T.productService.controller;


import com.rent.T.productService.dto.ProductRequest;
import com.rent.T.productService.dto.ProductResponse;
import com.rent.T.productService.models.Product;
import com.rent.T.productService.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse saved = productService.createProduct(productRequest);
        if (saved != null)
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProduct() {
        try {       //sleep of 5 sec to respond a slowly responding api for our circuit breakers -> if 5 sec delay for 5 times -> circuit breaker opens, then instantaneously it will give             Thread.sleep(5000);
            Thread.sleep(0);    //make if 5000 to check
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }
}
