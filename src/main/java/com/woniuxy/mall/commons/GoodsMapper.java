package com.woniuxy.mall.commons;

import com.woniuxy.mall.entity.Goods;
import com.woniuxy.mall.pojo.GoodsData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GoodsMapper {
    List<GoodsData> map(List<Goods> menus);
}
