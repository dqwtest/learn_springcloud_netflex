package com.example.spring.cloud.netflix.consumer.service;

import com.example.spring.cloud.netflix.consumer.service.fallback.EchoServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 在接口启用 FeignClient
@FeignClient(name = "eureka-provider" , url = "localhost:8087", fallback = EchoServiceFallback.class)
@Primary
public interface EchoService {

    @GetMapping(value="/echo/{string}")
    public String echo(@PathVariable("string") String string);

    @GetMapping("/lb")
    public String lb();

    @GetMapping("/add")
    public String add(@RequestParam Integer a,@RequestParam Integer b);

    @GetMapping("/getUserIdByExtendCommand/{id}")
    public String getuserIdByExtendCommand();

}
