package com.woniuxy.mall.portal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mall.entity.Goods;
import com.woniuxy.mall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("portalGoodsController")
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/view")
    public String show(Model model, Integer id) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Goods goods = goodsService.getOne(queryWrapper);
        model.addAttribute("goods", goods);
        return "goods_view";
    }
}
