package com.woniuxy.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.woniuxy.mall.entity.Role;
import com.woniuxy.mall.mapper.RoleMapper;
import com.woniuxy.mall.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
