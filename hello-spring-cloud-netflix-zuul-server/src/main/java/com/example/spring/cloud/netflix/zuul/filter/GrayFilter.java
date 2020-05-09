package com.example.spring.cloud.netflix.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.*;

public class GrayFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // a filter has already forwarded
        // a filter has already determined serviceId
        return !ctx.containsKey(FORWARD_TO_KEY)
                && !ctx.containsKey(SERVICE_ID_KEY);
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("这是灰度发布 GrayFilter");
        // 从RequestContext获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 从上下文获取 HttpServletReuqest
        HttpServletRequest request = ctx.getRequest();
        String mark = request.getHeader("gray_mark");
        if (!StringUtils.isEmpty(mark) && "enable".equals(mark)) {
            RibbonFilterContextHolder.getCurrentContext()
                    .add("host-mark", "gray-host");
        } else {
            RibbonFilterContextHolder.getCurrentContext()
                    .add("host-mark", "running-host");
        }
        return null;
    }
}
