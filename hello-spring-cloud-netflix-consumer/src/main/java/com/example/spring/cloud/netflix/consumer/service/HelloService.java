package com.example.spring.cloud.netflix.consumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @CacheResult
    @HystrixCommand(commandKey = "getUser")
    public String getUserToCommandKey(@CacheKey Long id) {
        String json = restTemplate.getForObject("http://localhost:8087/getUserIdByExtendCommand/{1}", String.class, id);
        System.out.println(json);
        return json;
    }

    // 清除缓存
    @CacheRemove(commandKey = "getUser")
    @HystrixCommand
    public String updateUser(@CacheKey Long id) {
        System.out.println("删除getUser缓存");
        return "update success";
    }
}
