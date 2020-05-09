package com.example.spring.cloud.netflix.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class FirstPreFilter extends ZuulFilter {
    /**
     * 指定需要执行该 Filter 的规则
     * @return true 则执行 run()
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 该 Filter 具体执行的活动
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("这是第一个自定义过滤Zuul Filter");
        return null;
    }

    /**
     * 指定该 Filter 的类型
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 指定该 Filter 执行的优先级
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }
}
