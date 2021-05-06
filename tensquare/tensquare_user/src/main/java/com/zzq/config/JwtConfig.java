package com.zzq.config;

import com.zzq.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author Zhang zq
 * @Date 2021/5/4 0:59
 * @Description  Jwt鉴权配置类
 */
@Configuration
public class JwtConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * 自定义拦截规则
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
    }
}
