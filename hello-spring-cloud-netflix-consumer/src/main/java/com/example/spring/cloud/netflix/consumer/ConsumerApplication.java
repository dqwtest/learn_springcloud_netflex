package com.example.spring.cloud.netflix.consumer;

import com.example.spring.cloud.netflix.consumer.configure.AvoidScan;
import com.example.spring.cloud.netflix.consumer.configure.LoadBalancedConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @EnableDiscoveryClient
 * spring cloud 定义了发现服务的常用抽象方法，屏蔽服务治理的实现细节
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix // 暴露hystrix 接口 引入依赖
@EnableCircuitBreaker // 集成断路器
@RibbonClient(name = "eureka-provider", configuration = LoadBalancedConfiguration.class)
//让Spring不去扫描@AvoidScan注解的类，因为我们的配置是对单个服务源生效，
// 所以不能应用于全局，如果不排除会报错
@ComponentScan(excludeFilters = {@ComponentScan.Filter
        (type = FilterType.ANNOTATION, value = {AvoidScan.class})})
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
