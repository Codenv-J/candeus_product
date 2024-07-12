package com.candeus.product.common.config;


import com.candeus.product.common.interceptor.AdminLoginInterceptor;
import com.candeus.product.common.interceptor.RefreshAdminTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author Codenv
 * @Date 2023/2/12 15:54 （可以根据需要修改）
 * @Version 1.0 （版本号）
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置管理员接口拦截器
        registry.addInterceptor(new AdminLoginInterceptor())
                .addPathPatterns(
                        "/admin/**"
                )
                .excludePathPatterns(
                        "/admin/login"
                )
                .order(1);
        registry.addInterceptor(new RefreshAdminTokenInterceptor(stringRedisTemplate))
                .addPathPatterns("/admin/**").order(0);
    }

}
