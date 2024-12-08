package com.rent.T.productService.repository;

import com.rent.T.productService.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {       //<generic arg, strign>
}
