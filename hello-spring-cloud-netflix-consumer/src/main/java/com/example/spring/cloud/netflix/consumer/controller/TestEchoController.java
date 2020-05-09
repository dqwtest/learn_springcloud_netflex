package com.example.spring.cloud.netflix.consumer.controller;

import brave.propagation.ExtraFieldPropagation;
import com.example.spring.cloud.netflix.consumer.service.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RestController
public class TestEchoController {

    private static final Logger log = LoggerFactory.getLogger(TestEchoController.class);

    @Autowired
    private EchoService echoService;

    @Autowired
    private ExecutorService executorService;


    @GetMapping(value = "/echo/{str}")
    public String echoByFeign(@PathVariable("str") String str) {
        log.info("Client sent. Feign方式, 参数：{}", str);
        String result = echoService.echo(str);
        log.info("Client received. Feign方式, 结果：{}", result);
        return result;
    }

    @GetMapping(value = "/echoByThread/{str}")
    public String echo(@PathVariable("str") String str) throws ExecutionException, InterruptedException {
        log.info("Client sent. 子线程方式, 参数：{}", str);
        Future future = executorService.submit(() -> {
            log.info("Client sent. 进入子线程, 参数：{}", str);
            String result = echoService.echo(str);
            return result;
        });
        String result = (String)future.get();
        log.info("Client received, 返回主线程, 结果：{}", result);
        return result;
    }

    @GetMapping(value = "/lb")
    public String lb() {
        return echoService.lb();
    }

    @GetMapping(value = "/add")
    public String add(Integer a, Integer b) {
        return echoService.add(a, b);
    }
}
