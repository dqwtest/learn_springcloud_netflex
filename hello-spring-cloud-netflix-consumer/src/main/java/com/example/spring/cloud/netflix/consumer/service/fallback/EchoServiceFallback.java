package com.example.spring.cloud.netflix.consumer.service.fallback;

import com.example.spring.cloud.netflix.consumer.service.EchoService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EchoServiceFallback implements EchoService {

    @Override
    public String echo(String string) {
        return "你的网络有问题";
    }

    @Override
    public String lb() {
        return "请联系管理员";
    }

    @Override
    public String add(Integer a, Integer b) {
        return "无法计算";
    }

    @Override
    public String getuserIdByExtendCommand() {
        return "获取失败";
    }

}
