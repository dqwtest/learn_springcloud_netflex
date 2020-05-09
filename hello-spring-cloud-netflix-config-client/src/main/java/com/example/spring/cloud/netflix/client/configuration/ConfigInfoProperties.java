package com.example.spring.cloud.netflix.client.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties(prefix = "cn.springcloud.book")
public class ConfigInfoProperties {

    // @Value("${cn.springcloud.book.config}")
    private String config;

    public String getConfig() {
        return this.config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
