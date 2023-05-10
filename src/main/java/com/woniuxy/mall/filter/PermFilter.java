package com.woniuxy.mall.filter;

import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import com.woniuxy.mall.entity.Perm;
import com.woniuxy.mall.mallenum.ResponseCode;
import com.woniuxy.mall.service.PermService;
import com.woniuxy.mall.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
public class PermFilter implements Filter {

    @Autowired
    private PermService permService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("PermFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        String path = req.getRequestURI();
        log.debug("访问的路径：{}", path);
        //针对特定的路径（登录，获取菜单）直接放行
        if (path.equals("/api/admin/login") || path.equals("/api/menu/list")||path.equals("/api/captcha")) {
            chain.doFilter(request, response);
            return;
        }
        //获取jwt，解析其中的用户id
        String authorization = req.getHeader("authorization");
        JWT jwt = JWT.of(authorization);
        Object id = jwt.getPayload("id");
        List<Perm> perms = permService.getPermsByUserId(Integer.parseInt((String) id));

        //查询其权限，对比权限中的路径是否和访问的路径匹配
        for (Perm perm : perms) {
            if (path.equals(perm.getUrl())) {
                chain.doFilter(request, response);
                return;
            }
        }
        //无权限
        ResponseResult<Void> responseResult = new ResponseResult<>(ResponseCode.NO_AUTHED);
        String s = JSONUtil.toJsonStr(responseResult);
        response.getWriter().write(s);
        response.getWriter().close();
    }
}
