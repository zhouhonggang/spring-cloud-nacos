package com.zhou.javakc.gateway.forwarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                // 配置路由,把/system-user下的请求路由到system-user微服务中
//                .route(p -> p
//                        .path("/system-user/**")
//                        .filters(f -> f
//                                // 对path()指定的请求使用熔断器
//                                .hystrix(config -> config
//                                        // 熔断器的名字
//                                        .setName("default")
//                                        // 熔断到 /fallback 指向 fallback()
//                                        .setFallbackUri("forward:/fallback")))
//                        // lb开头是注册中心中的服务, http开头是域名地址
//                        .uri("lb://system-user"))
//                .route(p -> p
//                        .path("/oauth/token/**")
//                        .uri("lb://security-oauth2"))
//                .build();
//    }

    /**
     * 启用网关负载均衡
     * @param loadBalance 负载均衡
     * @return 负载均衡拦截器
     */
    @Bean
    LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance) {
        return new LoadBalancerInterceptor(loadBalance);
    }

    /**
     * 配置熔断器
     */
    @RestController
    static class FallbackController {
        @GetMapping("/fallback")
        public Mono<String> fallback() {
            return Mono.just("非常抱歉, 服务器开了点小差导致无法访问, 请稍后重试!");
        }
    }

    /**
     * 配置主页面
     */
    @Controller
    static class MainController {
        @RequestMapping("/")
        public String main()
        {
            return "index";
        }
    }

}
