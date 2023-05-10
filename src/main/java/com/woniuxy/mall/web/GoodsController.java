package com.woniuxy.mall.web;

import com.woniuxy.mall.entity.Goods;
import com.woniuxy.mall.service.GoodsService;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @PostMapping("/add")
    public ResponseResult<Void> add(@RequestBody Goods goods){
        goods.setStatus("y");
        goods.setIsDel("n");
        goodsService.save(goods);
        return ResponseResult.ok();
    }

    @GetMapping("list")
    public ResponseResult<List<Goods>> list(){
        return null;
    }
}
