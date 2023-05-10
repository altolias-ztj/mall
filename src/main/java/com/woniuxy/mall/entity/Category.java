package com.woniuxy.mall.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Category implements Serializable {
    private  Integer id;
    private String name;
    private String show;
    private Integer sort;
    private LocalDateTime lastUpdatetime;
    private String isDel;
    private Integer pid;
}
