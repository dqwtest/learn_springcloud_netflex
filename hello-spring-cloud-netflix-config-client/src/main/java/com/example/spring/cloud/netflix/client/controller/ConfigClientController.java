package com.example.spring.cloud.netflix.client.controller;

import com.example.spring.cloud.netflix.client.configuration.ConfigInfoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ConfigClientController {

    @Autowired
    private ConfigInfoProperties configInfoProperties;

    @RequestMapping("/getConfigInfo")
    public String getConfigInfo() {
        String result = configInfoProperties.getConfig();
        System.out.println(result);
        return result;
    }
}