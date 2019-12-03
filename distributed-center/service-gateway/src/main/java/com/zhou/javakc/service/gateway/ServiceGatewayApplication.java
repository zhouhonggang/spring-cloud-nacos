package com.zhou.javakc.service.gateway;

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
 * 服务网关转发(SpringCloudGateWay)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2019-12-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ServiceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceGatewayApplication.class, args);
    }

    @GetMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("非常抱歉, 服务器开了点小差!");
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 配置路由,把/test-module/下的请求路由到test-module微服务中
                .route(p -> p
                        .path("/test-module/**")
                        .filters(f -> f
                                // 对path()指定的请求使用熔断器
                                .hystrix(config -> config
                                        // 熔断器的名字
                                        .setName("default")
                                        // 熔断到 /fallback 指向 fallback()
                                        .setFallbackUri("forward:/fallback")))
                        // lb开头是注册中心中的服务, http开头是域名地址
                        .uri("lb://test-module"))
                .build();
    }

}
