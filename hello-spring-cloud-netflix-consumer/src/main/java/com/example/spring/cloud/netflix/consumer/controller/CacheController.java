package com.example.spring.cloud.netflix.consumer.controller;

import com.example.spring.cloud.netflix.consumer.service.HelloCommand;
import com.example.spring.cloud.netflix.consumer.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CacheController {

    private final Logger logger = LoggerFactory.getLogger("测试");

    @Autowired
    private HelloService helloService;

    private final RestTemplate restTemplate;

    @Autowired
    public CacheController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //    @Autowired
//    private HelloCommand helloCommand;

    @GetMapping("/getUserIdByExtendCommand/{id}")
    public String getUserIdByExtendCommand(@PathVariable("id")Long id) {
        HelloCommand one = new HelloCommand(restTemplate, id);
        // 执行同步阻塞调用
        one.execute();
        logger.info("from cache: " + one.isResponseFromCache());
        HelloCommand two = new HelloCommand(restTemplate, id);
        two.execute();
        logger.info("from cache: " + two.isResponseFromCache());
        return "success";
    }

    @GetMapping(value = "/getUser/{id}")
    public String getUserId(@PathVariable("id")Long id) {
        helloService.getUserToCommandKey(id);
        helloService.getUserToCommandKey(id);
        return "getUser success";
    }

    @GetMapping(value = "/getAndUpdateUser/{id}")
    public String getAndUpdateUser(@PathVariable("id") Long id) {
        // 调用接口并缓存数据
        helloService.getUserToCommandKey(id);
        helloService.getUserToCommandKey(id);
        // 清除缓存
        helloService.updateUser(id);
        // 再调用接口
        helloService.getUserToCommandKey(id);
        helloService.getUserToCommandKey(id);
        return "getAndUpdateUser success";
    }
}
