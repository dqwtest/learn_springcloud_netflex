package com.example.spring.cloud.netflix.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

public class SecondPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("这是SecondPreFilter! ");
        // 从RequestContext获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 从上下文获取 HttpServletReuqest
        HttpServletRequest request = ctx.getRequest();
        // 从 request 尝试获取 a 参数值
        String a = request.getParameter("a");
        // 如果 a 参数值为空则进入此逻辑
        if (null == a) {
            // 对该请求禁止路由，也就是禁止访问下游服务
            ctx.setSendZuulResponse(false);
            // 设定responseBody供PostFilter使用
            ctx.setResponseBody("{\"status\":500, \"message\":\"a参数为空! \"}");
            // logic-is-success 保存于上下文，作为同类型下游 Filter 的执行开关
            ctx.set("logic-is-success", false);
            // 到这里此 Filter 逻辑结束
            return null;
        }
        // 设置避免报空
        ctx.set("logic-is-success", true);
        return null;
    }
}
