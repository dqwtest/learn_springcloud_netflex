package com.example.spring.cloud.netflix.zuul.domain;

public class ZuulRouteEntity {
    private long id;
    private String path;
    private String service_id;
    private String url;
    private int strip_prefix;
    private int retryable;
    private int enable;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStrip_prefix() {
        return strip_prefix;
    }

    public void setStrip_prefix(int strip_prefix) {
        this.strip_prefix = strip_prefix;
    }

    public int getRetryable() {
        return retryable;
    }

    public void setRetryable(int retryable) {
        this.retryable = retryable;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
