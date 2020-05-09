package com.example.spring.cloud.netflix.consumer.filter;

import brave.propagation.ExtraFieldPropagation;
import org.springframework.cloud.sleuth.instrument.web.SleuthWebProperties;
import org.springframework.cloud.sleuth.instrument.web.TraceWebServletAutoConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 自定义过滤器,
 * 获取当前的 SessionId，放入Baggage中
 * 注意，因为不是所有的请求都需要往后传递，所以会对一些请求跳过执行
 */
@Component
@Order(TraceWebServletAutoConfiguration.TRACING_FILTER_ORDER + 1)
public class SessionFilter extends GenericFilterBean {

    private Pattern skipPattern = Pattern.compile(SleuthWebProperties.DEFAULT_SKIP_PATTERN);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(!(servletRequest instanceof HttpServletRequest) ||
                !(servletResponse instanceof HttpServletResponse)) {
            throw new ServletException("只支持 Http 协议");
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        // 验证匹配
        boolean skip = skipPattern.matcher(httpServletRequest.getRequestURI()).matches();
        if(!skip) {
            // 将 SessionId 放到 Baggage 中
            String SessionId = httpServletRequest.getSession().getId();
            System.out.println("放入SessionId: " + SessionId);
            ExtraFieldPropagation.set("SessionId", SessionId);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
