package com.woniuxy.mall.web;

import com.woniuxy.mall.service.MinioService;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Resource
    private MinioService minioService;

    @PostMapping("goods")
    public ResponseResult<String> uploadGoodsImage(MultipartFile goodsImage) {
        String url = minioService.uploadToMinIO(goodsImage);
        return new ResponseResult<>(url);
    }
}
