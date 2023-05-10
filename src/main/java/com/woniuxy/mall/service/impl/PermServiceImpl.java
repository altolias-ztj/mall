package com.woniuxy.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.mall.entity.Perm;
import com.woniuxy.mall.mapper.PermMapper;
import com.woniuxy.mall.service.PermService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, Perm> implements PermService {
    @Resource
    private PermMapper permMapper;

    @Override
    public List<Perm> getPermsByUserId(int id) {
        return permMapper.getPermsByUserId(id);
    }
}
