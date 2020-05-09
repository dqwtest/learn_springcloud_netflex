package com.example.spring.cloud.netflix.consumer.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfiguration extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new CacheContextInterceptor())
                .addPathPatterns("/getUserIdByExtendCommand/*", "/getUser/*", "/getAndUpdateUser/*");
    }
}
