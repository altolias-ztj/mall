package com.woniuxy.mall.entity;

import lombok.Data;

@Data
public class Address {
    private Integer id;
    private Integer userId;
    private String name;
    private String phone;
    private String province;
    private String city;
    private String district;
    private String detail;
    private String isDefault;
}
