package com.example.spring.cloud.netflix.consumer.configure;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 初始化请求
public class CacheContextInterceptor implements HandlerInterceptor {

    private HystrixRequestContext context;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 初始化请求上下文
        context = HystrixRequestContext.initializeContext();
        // System.out.println("初始化请求上下文");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // System.out.println("上下文关闭");
        context.shutdown();
    }
}
