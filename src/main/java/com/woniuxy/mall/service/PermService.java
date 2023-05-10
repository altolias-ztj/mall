package com.woniuxy.mall.service;

import com.woniuxy.mall.entity.Perm;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermService {
    @Select("select * from v_perm where aid=#{id}")
    List<Perm> getPermsByUserId(int id);
}
