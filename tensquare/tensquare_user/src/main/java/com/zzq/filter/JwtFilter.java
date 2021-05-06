package com.zzq.filter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Zhang zq
 * @Date 2021/5/4 0:56
 * @Description  创建拦截器进行鉴权
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("经过了拦截器.....");
        final String authHeader = request.getHeader("Authorization");

        //前后端约定：前端请求微服务时需要添加头信息Authorization ,内容为Bearer+空格+token
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            //拿到token信息
            final String token = authHeader.substring(7);
            //解析token
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null){
                if ("admin".equals(claims.get("roles"))){
                    //说明是管理员
                    request.setAttribute("admin_claims",claims);
                }
                if ("user".equals(claims.get("roles"))){
                    //说明是用户
                    request.setAttribute("user_claims",claims);
                }
            }
        }
        return true;
    }
}
