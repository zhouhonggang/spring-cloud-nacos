package com.zhou.javakc.service.gateway.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * XXX 类描述信息
 *
 * @author zhou
 * @version v1.0.0
 * @project
 * @date 2019-12-10 10:41
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Component
public class Oauth2Filter implements GlobalFilter, Ordered {

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String accessToken = extractToken(exchange.getRequest());
        if(pathMatcher.match("/**/v2/api-docs/**",exchange.getRequest().getPath().value())){
            return chain.filter(exchange);
        }
        if(pathMatcher.match("/oauth/token/**",exchange.getRequest().getPath().value())){
            return chain.filter(exchange);
        }
        System.out.println( "网关拦截:"+exchange.getRequest().getPath().value() );
        if(!pathMatcher.match("/test-module/**",exchange.getRequest().getPath().value())){
            if (accessToken == null) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }else{
                try {
                    Map<String, Object> params =  (Map<String, Object>) redisTemplate.opsForValue().get("access:" + accessToken) ;
                    if(params.isEmpty()){
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                } catch (Exception e) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            }
        }
        return chain.filter(exchange);
    }

    protected String extractToken(ServerHttpRequest request) {
        List<String> strings = request.getHeaders().get("Authorization");
        String authToken = null;
        if (strings != null) {
            authToken = strings.get(0).substring("Bearer".length()).trim();
        }
        if (StringUtils.isBlank(authToken)) {
            strings = request.getQueryParams().get("access_token");
            if (strings != null) {
                authToken = strings.get(0);
            }
        }
        return authToken;
    }

    @Override
    public int getOrder() {
        return -500;
    }

}
