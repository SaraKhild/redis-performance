package com.example.redis.reactive.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.example.redis.reactive.model.Product;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

}