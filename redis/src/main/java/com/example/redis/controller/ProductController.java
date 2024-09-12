package com.example.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.model.Product;
import com.example.redis.service.ProductService;

import reactor.core.publisher.Mono;

@RestController("redis-data-product")
@RequestMapping("api/redis-data-product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public Mono<Product> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PutMapping("{id}")
    public Mono<Product> updateProduct(@PathVariable Integer id, @RequestBody Mono<Product> product) {
        return productService.updateProduct(id, product);
    }

    @PostMapping
    public Mono<Product> postProduct(@RequestBody Mono<Product> product) {
        return product.flatMap(productService::saveProduct);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }

}