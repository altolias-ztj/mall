package com.woniuxy.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.mall.entity.Menu;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    @Select("select * from v_menu where aid=#{id}")
    List<Menu> getMenusByUserId(int id);
}
