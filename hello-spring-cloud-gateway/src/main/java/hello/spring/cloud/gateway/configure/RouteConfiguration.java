package hello.spring.cloud.gateway.configure;

import hello.spring.cloud.gateway.filter.CustomGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // basic proxy
                .route(r -> r.path("/jd")
                        .uri("http://jd.com.80/").id("jd_route")
                ).build();
    }

//    @Bean
//    public RouteLocator cookieRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                // basic proxy
//                .route("cookie_route", r -> r
//                        .cookie("chocolate", "ch.p")
//                        .uri("http://localhost:8087/test/cookie")
//                ).build();
//    }

    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/test")
                .filters(f -> f.filter(new CustomGatewayFilter()))
                        .uri("http://baidu.com:80")
                        .order(0)
                        .id("custom_filter")
                ).build();
    }
}
