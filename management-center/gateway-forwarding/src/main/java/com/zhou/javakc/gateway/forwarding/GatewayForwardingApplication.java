package com.zhou.javakc.gateway.forwarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 网管转发(Spring Cloud GateWay)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2020-3-8 09:12
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayForwardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayForwardingApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 配置路由,把/system-user下的请求路由到system-user微服务中
                .route(p -> p
                        .path("/system/user/**")
                        .filters(f -> f
                                // 对path()指定的请求使用熔断器
                                .hystrix(config -> config
                                        // 熔断器的名字
                                        .setName("default")
                                        // 熔断到 /fallback 指向 fallback()
                                        .setFallbackUri("forward:/fallback")))
                        // lb开头是注册中心中的服务, http开头是域名地址
                        .uri("lb://system-user"))
                .route(p -> p
                        .path("/oauth/token/**")
                        .uri("lb://security-oauth2"))
                .route(p -> p
                        .path("/qq")
                        .uri("https://news.qq.com/"))
                .build();
    }

    @RestController
    static class FallbackController {
        @GetMapping("/fallback")
        public Mono<String> fallback() {
            return Mono.just("非常抱歉, 服务器开了点小差导致无法访问, 请稍后重试!");
        }
    }

}