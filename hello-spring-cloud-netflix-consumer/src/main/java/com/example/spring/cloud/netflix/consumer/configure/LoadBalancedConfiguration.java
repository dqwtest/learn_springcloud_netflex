package com.example.spring.cloud.netflix.consumer.configure;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AvoidScan
public class LoadBalancedConfiguration {

    @Autowired
    private IClientConfig config;

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        // 使用随机策略
        return new RoundRobinRule();
    }
}
