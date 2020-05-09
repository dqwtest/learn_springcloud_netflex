package com.example.spring.cloud.netflix.consumer.service;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategyDefault;
import org.springframework.web.client.RestTemplate;

public class HelloCommand extends HystrixCommand {

    private Long id;
    private RestTemplate restTemplate;

    public HelloCommand(RestTemplate restTemplate, Long id) {
        // 设置服务分组
        super((Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("HelloWorldGroup"))
        // 设置以来超时时间 500 毫秒
        .andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)))
        // 设置 命令 key
        .andCommandKey(
                HystrixCommandKey.Factory.asKey("test")));
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected String run() throws Exception {
        String json = restTemplate.getForObject("http://localhost:8087/getUserIdByExtendCommand/{1}", String.class, id);
        System.out.println(json);
        return json;
    }

    /**
     * 开启请求缓存，只需重载getCacheKey方法
     * 因为我们这里使用的是id，不同的请求来请求的时候会有不同cacheKey所以，同一请求第一次访问会调用，之后都会走缓存
     * 好处：    1.减少请求数、降低并发
     *          2.同一用户上下文数据一致
     *          3.这个方法会在run()和contruct()方法之前执行，减少线程开支
     */
    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }

    /**
     * 清理缓存
     * 开启请求缓存之后，我们在读的过程中没有问题，但是我们如果是写，那么我们继续读之前的缓存了
     * 我们需要把之前的cache清掉
     * 说明 ：   1.其中getInstance方法中的第一个参数的key名称要与实际相同
     *          2.clear方法中的cacheKey要与getCacheKey方法生成的key方法相同
     *          3.注意我们用了commandKey是test,大家要注意之后new这个Command的时候要指定相同的commandKey,否则会清除不成功
     */
    public static void flushRequestCache(Long id){
        HystrixRequestCache.getInstance(
                HystrixCommandKey.Factory.asKey("test"), HystrixConcurrencyStrategyDefault.getInstance())
                .clear(String.valueOf(id));
    }
}
