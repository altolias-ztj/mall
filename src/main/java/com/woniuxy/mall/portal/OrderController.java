package com.woniuxy.mall.portal;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mall.entity.*;
import com.woniuxy.mall.service.AddressService;
import com.woniuxy.mall.service.GoodsService;
import com.woniuxy.mall.service.OrderService;
import com.woniuxy.mall.utils.MallUtil;
import com.woniuxy.mall.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private GoodsService goodsService;

    @GetMapping("confirm")
    public String confirm(@RequestParam("id") Integer goodsId, Integer num, Model model, HttpSession session) {
        //1，根据userid获取其收货底地址，选中默认地址
        User user = (User) session.getAttribute("user");
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getId());
        List<Address> addressList = addressService.list(queryWrapper);
        //2,根据goodsId查询商品信息（id，图片，名称，单价）
        model.addAttribute("addressList", addressList);

        Goods goods = goodsService.getById(goodsId);

        GoodsVO goodsVO = new GoodsVO();
        goodsVO.setGoods(goods);
        goodsVO.setNum(num);

        model.addAttribute("goodsVoList", Arrays.asList(goodsVO));
        return "order_confirm";
    }

    @Autowired
    private OrderService orderService;

    @PostMapping("add")
    public String add(Order order, HttpServletRequest request, int[] goodsid, int[] nums, HttpSession session) {
        String[] goodsids = request.getParameterValues("goodsid");

        User user = (User) session.getAttribute("user");
        order.setNo(MallUtil.createOrderNo());
        order.setTime(LocalDateTime.now());
        order.setUserId(user.getId());
        order.setStatus("y");

        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < goodsid.length; i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setGoodsId(goodsid[i]);
            orderItem.setNums(nums[i]);
            orderItems.add(orderItem);
        }
        orderService.add(order, orderItems);
        return "order_add";
    }
}
