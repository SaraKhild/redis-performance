package com.example.redis.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.redis.model.Product;

@Repository("redis-product")
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

}