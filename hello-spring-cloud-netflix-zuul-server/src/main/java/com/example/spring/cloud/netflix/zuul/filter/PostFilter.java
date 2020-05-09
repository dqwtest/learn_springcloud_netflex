package com.example.spring.cloud.netflix.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;

/**
 * 检查有无定制 ResponseBody
 * 设置响应字符，设定状态码
 */
public class PostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("这是PostFilter! ");
        // 从RequestContext获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 处理返回中文乱码
        ctx.getResponse().setCharacterEncoding("UTF-8");
        ctx.getResponse().setHeader("Content-type","text/html;charset=utf-8");
        // 获取上下文保存的 responseBody
        String responseBody = ctx.getResponseBody();
        // 如果 responseBody不为空，则说明流程有异常发生
        if (null != responseBody) {
            // 设定返回状态码
            ctx.setResponseStatusCode(500);
            // 替换响应报文
            ctx.setResponseBody(responseBody);
            // 到这里此 Filter 逻辑结束
            return null;
        }
        return null;
    }
}
