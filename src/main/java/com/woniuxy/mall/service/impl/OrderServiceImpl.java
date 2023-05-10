package com.woniuxy.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.mall.entity.Goods;
import com.woniuxy.mall.entity.Order;
import com.woniuxy.mall.entity.OrderItem;
import com.woniuxy.mall.mapper.GoodsMapper;
import com.woniuxy.mall.mapper.OrderItemMapper;
import com.woniuxy.mall.mapper.OrderMapper;
import com.woniuxy.mall.service.OrderService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Transactional
    @Override
    public void add(Order order, List<OrderItem> orderItems) {
        //计算总金额
        BigDecimal total = new BigDecimal("9.9");
        for (OrderItem orderItem : orderItems) {
            BigDecimal price = goodsMapper.selectById(orderItem.getGoodsId()).getSalesPrice();
            total = total.add(price.multiply(new BigDecimal(orderItem.getNums())));
        }
        order.setTotal(total);
        //1，增加订单表的数据
        orderMapper.insert(order);

        //2，增加若干订单明细表的数据
        for (OrderItem orderItem : orderItems) {
            Goods goods = goodsMapper.selectById(orderItem.getGoodsId());
            orderItem.setOrderId(order.getId());
            orderItem.setNums(orderItem.getNums());
            orderItem.setName(goods.getName());
            orderItem.setPrice(goods.getSalesPrice());
            orderItemMapper.insert(orderItem);
        }
    }

    //将商品库存减少，先判断库存是否足够
    public String cancel() {
        return "";
    }
}
