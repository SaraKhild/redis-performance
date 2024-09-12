package com.example.redis.reactive.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table
public class Product {

    @Id
    private Integer id;
    private String title;
    private String price;

}