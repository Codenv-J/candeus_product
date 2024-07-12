package com.candeus.product.common.interceptor;


import com.candeus.product.tool.AdminHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Author Codenv
 * @Date 2023/2/12 15:49 （可以根据需要修改）
 * @Version 1.0 （版本号）
 */
public class AdminLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否要拦截
        if (AdminHolder.getAdmin() == null){
            response.setStatus(401);
            return false;
        }
        //有用户，则放行
        return true;
    }
}
