package com.candeus.product.common.interceptor;

import cn.hutool.core.bean.BeanUtil;
import com.candeus.product.domain.vo.AdminVo;
import com.candeus.product.tool.AdminHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.candeus.product.common.constant.RedisConstants.LOGIN_ADMIN_TTL;


/**
 * @Author Codenv
 * @Date 2023/2/12 15:49 （可以根据需要修改）
 * @Version 1.0 （版本号）
 */
@Slf4j
public class RefreshAdminTokenInterceptor implements HandlerInterceptor {

    private StringRedisTemplate stringRedisTemplate;

    public RefreshAdminTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token
        String token = request.getHeader("authorization");
        //获取redis中的用户
        String key = "candeus:product:admin:token:" + token;
        Map<Object, Object> adminMap = stringRedisTemplate.opsForHash().entries(key);
        //判断map是否为空
        if (adminMap.isEmpty()){
            return true;
        }
        //转化为用户对象
        AdminVo admin = BeanUtil.fillBeanWithMap(adminMap, new AdminVo(), false);
        AdminHolder.saveAdmin(admin);
        //刷新token有效期
        stringRedisTemplate.expire(key,LOGIN_ADMIN_TTL, TimeUnit.MINUTES);
        //放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AdminHolder.removeAdmin();
    }
}
