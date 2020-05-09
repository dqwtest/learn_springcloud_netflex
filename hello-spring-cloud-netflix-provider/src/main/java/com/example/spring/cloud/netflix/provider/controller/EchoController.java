package com.example.spring.cloud.netflix.provider.controller;

import brave.propagation.ExtraFieldPropagation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class EchoController {

    private static final Logger log = LoggerFactory.getLogger(EchoController.class);

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/echo/{string}")
    public String echo(@PathVariable("string") String string) {
        log.info("server received. 参数: {}" + string);
        String result = "Hello eureka Provider: " + string + ", SessionId is " +
                ExtraFieldPropagation.get("SessionId");
        log.info("server sent. 结果: {}" + result);
        return result;
    }

    @GetMapping(value = "/lb")
    public String lb() {
        return "Hello Nacos Provider i am from port:" + port;
    }

    @GetMapping(value = "/add")
    public String add(Integer a,Integer b) {
        int result = a + b;
        return "答案是：" + result +" 端口号: " + port;
    }

    @GetMapping("/test/cookie")
    public String testGateway(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for(Cookie cookie: cookies) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        return "cookie";
    }
}
