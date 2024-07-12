package com.candeus.product.common.log;

import com.candeus.product.tool.AdminHolder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Aspect
@Component
public class AopLog {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper mapper = new ObjectMapper();

    // 定义切点
    @Pointcut(value = "execution(* com.candeus.product.controller.*.*(..))")
    public void aopWebLog() {
    }

    // 使用环绕通知
    @Around("aopWebLog()")
    public Object myLogger(ProceedingJoinPoint pjp) throws Throwable {
        // 使用ServletRequestAttributes请求上下文获取方法更多
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        Object[] array = pjp.getArgs();
        String requestId = String.valueOf(UUID.randomUUID());
        MDC.put("requestId", requestId);

        // 执行函数前打印日志
        if (AdminHolder.getAdmin() != null) {
            logger.info("requestId：{} 管理员信息：{}", requestId, AdminHolder.getAdmin().getAdminId());
        }

        logger.info("requestId：{} 调用前：{}：{}, 传递的参数为：{}", requestId, className, methodName, serializeArguments(array));
        logger.info("requestId：{} URL: {}", requestId, request.getRequestURL().toString());
        logger.info("requestId：{} URI: {}", requestId, request.getRequestURI());
        logger.info("requestId：{} 请求信息：{}", requestId, getRequestInfo(request));

        // 调用目标函数执行
        Object obj = pjp.proceed();

        // 执行函数后打印日志
        logger.info("requestId：{} 调用后：{}：{}, 返回值为：{}", requestId, className, methodName, serializeObject(obj));

        return obj;
    }

    private String getRequestInfo(HttpServletRequest request) {
        Map<String, Object> info = new HashMap<>();
        info.put("URL", request.getRequestURL().toString());
        info.put("Method", request.getMethod());
        info.put("Parameters", request.getParameterMap());
        // 添加你感兴趣的其他属性
        try {
            return mapper.writeValueAsString(info);
        } catch (JsonProcessingException e) {
            return "Failed to serialize request info";
        }
    }

    private String serializeArguments(Object[] args) {
        List<Object> serializableArgs = new ArrayList<>();
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                // 如果是 HttpServletRequest，则跳过序列化
                serializableArgs.add(getRequestInfo((HttpServletRequest) arg));
            } else {
                // 其他参数直接添加
                serializableArgs.add(arg);
            }
        }
        try {
            return mapper.writeValueAsString(serializableArgs);
        } catch (JsonProcessingException e) {
            return "Failed to serialize arguments";
        }
    }

    private String serializeObject(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "Failed to serialize object";
        }
    }
}
