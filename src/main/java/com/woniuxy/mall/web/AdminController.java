package com.woniuxy.mall.web;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.woniuxy.mall.entity.Admin;
import com.woniuxy.mall.service.AdminService;
import com.woniuxy.mall.vo.AdminVO;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import static com.woniuxy.mall.mallenum.ResponseCode.NOT_LOGIN;
import static com.woniuxy.mall.mallenum.ResponseCode.NO_AUTHED;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Resource
    private AdminService adminService;
    @Value("${JWT.secretKey}")
    private String secretKey;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody AdminVO adminVO, HttpServletResponse response) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", adminVO.getAccount());
        Admin admin = adminService.getOne(queryWrapper);
        if (admin == null) {
            return new ResponseResult<>(NOT_LOGIN);
        }
        if (!admin.getPassword().equals(DigestUtil.md5Hex(adminVO.getPassword()))) {
            return new ResponseResult<>(NO_AUTHED);
        }
        String token = JWT.create()
                .setPayload("id", String.valueOf(admin.getId()))
                .setPayload("account", String.valueOf(admin.getAccount()))
                .setKey(secretKey.getBytes())
                .sign();
        response.setHeader("Authorization", token);
        return ResponseResult.ok();
    }

    @GetMapping("/test")
    public ResponseResult<Void> test(@RequestHeader String authorization) {
        byte[] key = secretKey.getBytes();
        System.out.println(JWT.of(authorization).setKey(key).verify());
        return ResponseResult.ok();
    }
}
