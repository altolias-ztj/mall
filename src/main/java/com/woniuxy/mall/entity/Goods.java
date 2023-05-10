package com.woniuxy.mall.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Goods {
    private Integer id;
    private String name;
    private String image;
    private Integer categoryId;
    private BigDecimal marketPrice;
    private BigDecimal salesPrice;
    private String description;
    private Integer stock;
    private String hottest;
    private String newest;
    private String status;
    private String isDel;
}
