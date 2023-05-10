package com.woniuxy.mall.web;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.woniuxy.mall.vo.CaptchaVO;
import com.woniuxy.mall.vo.ResponseResult;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api")
public class CaptchaController {
    @Resource
    private RedisTemplate redisTemplate;

    @RequestMapping("/captcha")
    public ResponseResult<CaptchaVO> captcha(HttpServletRequest request, HttpServletResponse response){
        LineCaptcha lineCaptcha= CaptchaUtil.createLineCaptcha(200,100);
        String uuid= UUID.randomUUID().toString().replace("-","");
        String code=lineCaptcha.getCode();
        redisTemplate.opsForValue().set(uuid,code,30, TimeUnit.MILLISECONDS);
        String imageBase64=lineCaptcha.getImageBase64();
        CaptchaVO captchaVO=new CaptchaVO();
        captchaVO.setUuid(uuid);
        captchaVO.setCaptcha(imageBase64);
        return new ResponseResult<>(captchaVO);
    }
}
