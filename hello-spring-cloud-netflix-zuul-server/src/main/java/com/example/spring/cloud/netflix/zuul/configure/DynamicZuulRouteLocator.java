package com.example.spring.cloud.netflix.zuul.configure;

import com.example.spring.cloud.netflix.zuul.bean.PropertiesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;

public class DynamicZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    @Autowired
    private  ZuulProperties zuulProperties;

    @Autowired
    private PropertiesDao propertiesDao;

    public DynamicZuulRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.zuulProperties = properties;
    }

    /**
     * 刷新路由
     */
    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<>();
        // 从application.properties中加载静态路由信息
        routesMap.putAll(super.locateRoutes());
        // 从数据源中加载动态路由信息
        routesMap.putAll(propertiesDao.getProperties());
        // 优化一下配置
        LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
        for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.zuulProperties.getPrefix())) {
                path = this.zuulProperties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());
        }
        return values;
    }

}
