package com.woniuxy.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category{
    private  Integer id;
    private String name;
    private String show;
    private Integer sort;
    private LocalDateTime lastUpdatetime;
    private String isDel;
    private Integer pid;
}
