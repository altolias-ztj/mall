package com.woniuxy.mall;

import com.woniuxy.mall.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MenuTest {

    @Resource
    private MenuService menuService;

    @Test
    public void testSelect(){
        System.out.println(menuService.getMenuByUserId(1).get(0));
    }
}
