package com.woniuxy.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.mall.entity.Menu;
import com.woniuxy.mall.mapper.MenuMapper;
import com.woniuxy.mall.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> getMenuByUserId(int id) {
        return menuMapper.getMenusByUserId(id);
    }
}
