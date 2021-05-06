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
 * @Date 2021/5/4 15:14
 * @Description
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            //拿到头部携带的token
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if(claims != null){
                if("user".equals(claims.get("roles"))){
                    request.setAttribute("user_claims",claims);
                }
                if("admin".equals(claims.get("roles"))){
                    request.setAttribute("admin_claims",claims);
                }
            }
        }
        return true;
    }
}
