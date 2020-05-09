package com.example.spring.cloud.netflix.zuul.bean;

import com.example.spring.cloud.netflix.zuul.domain.ZuulRouteEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;

@Component
public class PropertiesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final static String SQL = "SELECT * FROM  zuul_route WHERE enabled = TRUE";

    public Map<String, ZuulRoute> getProperties() {
        Map<String, ZuulRoute> routes = new LinkedHashMap<>();
        List<ZuulRouteEntity> list = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(ZuulRouteEntity.class));
        list.forEach(entity -> {
            if (StringUtils.isEmpty(entity.getPath())) return;
            ZuulRoute zuulRoute = new ZuulRoute();
            BeanUtils.copyProperties(entity, zuulRoute);
            routes.put(zuulRoute.getPath(), zuulRoute);
        });
        return routes;
    }
}
