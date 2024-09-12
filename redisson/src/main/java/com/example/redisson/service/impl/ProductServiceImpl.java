package com.example.redisson.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.redis.reactive.util.CacheTemplate;
import com.example.redisson.model.Product;
import com.example.redisson.service.ProductService;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CacheTemplate<Integer, Product> chacheTemplate;

    @Override
    public Mono<Product> getProductById(Integer id) {
        return chacheTemplate.getData(id);
    }

    @Override
    public Mono<Product> updateProduct(Integer id, Mono<Product> product) {
        return product.flatMap(p -> chacheTemplate.updateData(id, p));
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return chacheTemplate.postData(product);
    }

    @Override
    public Mono<Void> deleteProduct(Integer id) {
        return chacheTemplate.deleteData(id).then();
    }

}