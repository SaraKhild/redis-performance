package com.example.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.redis.model.Product;
import com.example.redis.reactive.util.CacheTemplate;
import com.example.redis.repository.ProductRepository;

import reactor.core.publisher.Mono;

@Service("redisProductCacheTemplate")
public class ProductCacheTemplate extends CacheTemplate<Integer, Product> {

    private final ProductRepository productRepository;
    private static final String REDIS_PREFIX = "Product:";
    private final RedisTemplate<String, Product> redisTemplate;

    @Autowired
    public ProductCacheTemplate(RedisTemplate<String, Product> redisTemplate, ProductRepository productRepository) {
        this.redisTemplate = redisTemplate;
        this.productRepository = productRepository;
    }

    @Override
    protected Mono<Product> getDataFromSource(Integer key) {

        return this.productRepository.findById(key);
    }

    @Override
    protected Mono<Product> getDataFromCache(Integer key) {

        return Mono.justOrEmpty(redisTemplate.opsForHash().get(REDIS_PREFIX, key)).cast(Product.class);

    }

    @Override
    protected Mono<Product> postDataToSource(Product entity) {

        return productRepository.save(entity);
    }

    @Override
    protected Mono<Product> postDataToChach(Product entity) {

        return Mono.fromCallable(() -> {
            redisTemplate.opsForValue().set(REDIS_PREFIX + entity.getId(), entity);
            return entity;
        });

    }

    @Override
    protected Mono<Product> updateDataFromSource(Integer key, Product entity) {

        return productRepository.findById(key).doOnNext(product -> entity.setId(key))
                .flatMap(this.productRepository::save);
    }

    @Override
    protected Mono<Product> updateDataFromCache(Integer key, Product entity) {

        return Mono.fromCallable(() -> {
            redisTemplate.opsForHash().put(REDIS_PREFIX, key, entity);
            return entity;
        });

    }

    @Override
    protected Mono<Void> deleteDataFromSource(Integer key) {

        return productRepository.deleteById(key).then();
    }

    @Override
    protected Mono<Void> deleteDataFromCache(Integer key) {

        return Mono.fromCallable(() -> { // Mono.fromCallable(...) is a utility that lets you take a blocking operation
                                         // (like redisTemplate.delete(...)) and wrap it into a Mono. The operation is
                                         // executed when the Mono is subscribed to, but it won’t block the thread. This
                                         // is useful for integrating blocking code into reactive streams.
            redisTemplate.delete(REDIS_PREFIX + key);
            return null; // we return null to convert it to Void.
        });

    }

}