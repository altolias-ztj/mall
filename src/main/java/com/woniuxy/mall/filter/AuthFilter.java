package com.woniuxy.mall.filter;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import com.woniuxy.mall.mallenum.ResponseCode;
import com.woniuxy.mall.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthFilter implements Filter {

    @Value("${JWT.secretKey}")
    private String secretKey;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("AuthFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        String path = req.getRequestURI();
        log.debug("访问的路径：{}", path);
        if (path.equals("/api/admin/login")||path.equals("/api/captcha")) {
            chain.doFilter(request, response);
            return;
        }
        String authorization = req.getHeader("authorization");
        if (authorization == null || authorization.equals("")) {
            process(response);
        }
        boolean verify = JWT.of(authorization).setKey(secretKey.getBytes()).verify();
        try {
            if (verify) {
                chain.doFilter(request, response);
            } else {
                process(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            process(response);
        }
    }

    private static void process(ServletResponse response) throws IOException {
        ResponseResult<Void> responseResult = new ResponseResult<>(ResponseCode.NOT_LOGIN);
        String s = JSONUtil.toJsonStr(responseResult);
        response.getWriter().write(s);
        response.getWriter().close();
    }
}
