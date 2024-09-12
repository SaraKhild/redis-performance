package com.example.redis.reactive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.redis.reactive.model.Product;
import com.example.redis.reactive.service.ProductService;
import com.example.redis.reactive.util.CacheTemplate;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CacheTemplate<Integer, Product> cacheTemplate;

    @Override
    public Mono<Product> getProductById(Integer id) {
        return cacheTemplate.getData(id);
    }

    @Override
    public Mono<Product> updateProduct(Integer id, Mono<Product> product) {
        return product.flatMap(p -> cacheTemplate.updateData(id, p));
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return cacheTemplate.postData(product);
    }

    @Override
    public Mono<Void> deleteProduct(Integer id) {
        return cacheTemplate.deleteData(id).then();
    }

}