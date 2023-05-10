package com.woniuxy.mall.web;

import cn.hutool.jwt.JWT;
import com.woniuxy.mall.commons.MenuMapper;
import com.woniuxy.mall.entity.Menu;
import com.woniuxy.mall.mallenum.ResponseCode;
import com.woniuxy.mall.service.MenuService;
import com.woniuxy.mall.vo.MenuVO;
import com.woniuxy.mall.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult<List<MenuVO>> list(@RequestHeader String authorization) {
        log.debug("authorization:{}", authorization);
        JWT jwt = JWT.of(authorization);
        Object id = jwt.getPayload("id");
        List<Menu> menus = menuService.getMenuByUserId(Integer.parseInt((String) id));
        List<MenuVO> menuVOS = menuMapper.map(menus);
        return new ResponseResult<>(ResponseCode.SUCCESS, menuVOS);
    }
}
