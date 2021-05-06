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
 * @Date 2021/5/4 1:26
 * @Description  jwt鉴权过滤
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //拿到消息头部信息
        String authHeader = request.getHeader("Authorization");
        System.out.println("authHeader = " + authHeader);
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            //拿到头部信息中的token信息
            final String token = authHeader.substring(7);

            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null){
                if ("admin".equals(claims.get("roles"))){
                    request.setAttribute("admin_claims",claims);
                }
                if ("user".equals(claims.get("roles"))){
                    request.setAttribute("user_claims",claims);
                }
            }

        }

        return true;
    }
}
