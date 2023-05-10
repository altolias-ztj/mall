package com.woniuxy.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.mall.entity.Cart;
import com.woniuxy.mall.mapper.CartMapper;
import com.woniuxy.mall.mapper.GoodsMapper;
import com.woniuxy.mall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void add(Cart cart) {
        cart.setAddPrice(goodsMapper.selectById(cart.getGoods().getId()).getSalesPrice());
        cart.setIsChoose("y");
        //
        cartMapper.insert(cart);
    }
}
