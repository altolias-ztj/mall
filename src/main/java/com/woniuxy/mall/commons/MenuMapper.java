package com.woniuxy.mall.commons;

import com.woniuxy.mall.entity.Menu;
import com.woniuxy.mall.vo.MenuVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuVO map(Menu menu);

    List<MenuVO> map(List<Menu> menus);
}
