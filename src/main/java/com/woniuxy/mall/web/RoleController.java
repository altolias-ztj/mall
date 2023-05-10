package com.woniuxy.mall.web;

import com.woniuxy.mall.entity.Role;
import com.woniuxy.mall.service.RoleService;
import com.woniuxy.mall.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody Role role){
        roleService.save(role);
        return ResponseResult.ok();
    }

    @GetMapping("list")
    public ResponseResult<List<Role>> list(){
        return null;
    }
}
