package com.woniuxy.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.woniuxy.mall.entity.Cart;

public interface CartService extends IService<Cart> {
    void add(Cart cart);
}
