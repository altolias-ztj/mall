package com.woniuxy.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.woniuxy.mall.entity.Perm;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermMapper extends BaseMapper<Perm> {
    @Select("select * from v_perm where aid=#{id}")
    List<Perm> getPermsByUserId(int id);
}
