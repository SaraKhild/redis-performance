package com.example.redis.reactive.configration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.redis.reactive.model.Product;

@Configuration
public class RedisConfig {
 
 @Bean
 public ReactiveRedisTemplate<String, Product> reactiveRedisTemplate(ReactiveRedisConnectionFactory redisConnectionFactory) {
     RedisSerializationContext<String, Product> serializationContext = RedisSerializationContext
         .<String, Product>newSerializationContext(new StringRedisSerializer())  
         .hashValue(RedisSerializer.json()) 
         .build();

     return new ReactiveRedisTemplate<>(redisConnectionFactory, serializationContext);
 }

    @Bean
    public ReactiveRedisConnectionFactory connectionFactory() {
        return new LettuceConnectionFactory("localhost", 6379);
    }

}