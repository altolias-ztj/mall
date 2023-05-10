package com.woniuxy.mall.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItem {
    private Integer orderId;
    private Integer nums;
    private String name;
    private BigDecimal price;
    private Integer goodsId;
}
