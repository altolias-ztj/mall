package com.woniuxy.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.mall.entity.Order;
import com.woniuxy.mall.entity.OrderItem;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService extends IService<Order> {
    @Transactional
    void add(Order order, List<OrderItem> orderItems);
}
