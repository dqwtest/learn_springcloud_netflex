package com.example.spring.cloud.netflix.zuul.configure;

import com.example.spring.cloud.netflix.zuul.filter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfiguration {

    @Bean
    public FirstPreFilter firstPreFilter() {
        return new FirstPreFilter();
    }

    @Bean
    public SecondPreFilter secondPreFilter() {
        return new SecondPreFilter();
    }

    @Bean
    public ThirdPreFilter thirdPreFilter() {
        return new ThirdPreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    @Bean
    public GrayFilter grayFilter() {
        return new GrayFilter();
    }
}
