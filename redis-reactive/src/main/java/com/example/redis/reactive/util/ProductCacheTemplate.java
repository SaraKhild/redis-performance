package com.example.redis.reactive.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

import com.example.redis.reactive.model.Product;
import com.example.redis.reactive.repository.ProductRepository;

import reactor.core.publisher.Mono;

@Service
public class ProductCacheTemplate extends CacheTemplate<Integer, Product> {

    private final ProductRepository productRepository;
    private final ReactiveRedisTemplate<String, Product> redisTemplate;

    @Autowired
    public ProductCacheTemplate(ReactiveRedisTemplate<String, Product> redisTemplate, ProductRepository productRepository) {
        this.redisTemplate = redisTemplate;
        this.productRepository = productRepository;
    }

    @Override
    protected Mono<Product> getDataFromSource(Integer key) {
        return this.productRepository.findById(key);
    }

    @Override
    protected Mono<Product> getDataFromCache(Integer key) {
        return redisTemplate.opsForHash().get(key.toString(), key.toString()).cast(Product.class);
    }

    @Override
    protected Mono<Product> postDataToSource(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    protected Mono<Product> postDataToChach(Product entity) {
        return  redisTemplate.opsForHash().put(entity.getId().toString(),entity.getId().toString(), entity).thenReturn(entity);
    }

    @Override
    protected Mono<Product> updateDataFromSource(Integer key, Product entity) {
        return productRepository.findById(key).doOnNext(product -> entity.setId(key))
                .flatMap(this.productRepository::save);
    }

    @Override
    protected Mono<Product> updateDataFromCache(Integer key, Product entity) {
        return  redisTemplate.opsForHash().put(key.toString(), key.toString(), entity).thenReturn(entity);
    }

    @Override
    protected Mono<Void> deleteDataFromSource(Integer key) {
        return productRepository.deleteById(key).then();
    }

    @Override
    protected Mono<Void> deleteDataFromCache(Integer key) {
        return  redisTemplate.opsForHash().remove(key.toString(),key.toString()).then();

    }

}