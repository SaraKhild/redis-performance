package com.example.redis.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Table
public class Product implements Serializable {

    @Id
    private Integer id;
    private String title;
    private String price;

}