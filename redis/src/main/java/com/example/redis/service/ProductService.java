package com.example.redis.service;

import com.example.redis.model.Product;

import reactor.core.publisher.Mono;

public interface ProductService {

    public Mono<Product> getProductById(Integer id);

    public Mono<Product> updateProduct(Integer id, Mono<Product> product);

    public Mono<Product> saveProduct(Product product);

    public Mono<Void> deleteProduct(Integer id);

}