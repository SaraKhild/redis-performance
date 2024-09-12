package com.example.redisson.util;

import org.redisson.api.RMapReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.redis.reactive.util.CacheTemplate;
import com.example.redisson.model.Product;
import com.example.redisson.repository.ProductRepository;

import reactor.core.publisher.Mono;

@Service
public class ProductCacheTemplate extends CacheTemplate<Integer, Product> {

    private final ProductRepository productRepository;
    private final RMapReactive<Integer, Product> map;

    @Autowired
    public ProductCacheTemplate(RedissonReactiveClient client, ProductRepository productRepository) {
        this.map = client.getMap("product", new TypedJsonJacksonCodec(Integer.class, Product.class));
        this.productRepository = productRepository;
    }

    @Override
    protected Mono<Product> getDataFromSource(Integer key) {
        return this.productRepository.findById(key);
    }

    @Override
    protected Mono<Product> getDataFromCache(Integer key) {
        return map.get(key);
    }

    @Override
    protected Mono<Product> postDataToSource(Product entity) {
        return productRepository.save(entity);
    }

    @Override
    protected Mono<Product> postDataToChach(Product entity) {
        return map.fastPutIfAbsent(entity.getId(), entity).thenReturn(entity);
    }

    @Override
    protected Mono<Product> updateDataFromSource(Integer key, Product entity) {
        return productRepository.findById(key).doOnNext(product -> entity.setId(key))
                .flatMap(this.productRepository::save);
    }

    @Override
    protected Mono<Product> updateDataFromCache(Integer key, Product entity) {
        return map.fastPutIfExists(key, entity).thenReturn(entity);
    }

    @Override
    protected Mono<Void> deleteDataFromSource(Integer key) {
        return productRepository.deleteById(key).then();
    }

    @Override
    protected Mono<Void> deleteDataFromCache(Integer key) {
        return map.fastRemove(key).then();

    }

}