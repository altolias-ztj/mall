package com.woniuxy.mall.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Order {
    private Integer id;
    private String no;
    private LocalDateTime time;
    private Integer userId;
    private BigDecimal total;
    private Integer addressId;
    private String customer;
    private String phone;
    private String status;
}
