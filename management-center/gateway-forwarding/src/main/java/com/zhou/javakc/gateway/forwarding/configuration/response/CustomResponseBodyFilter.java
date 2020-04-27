package com.zhou.javakc.gateway.forwarding.configuration.response;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.zhou.javakc.gateway.forwarding.entity.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * 动态网关(Spring Cloud GateWay)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2020-3-8 09:12
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Slf4j
@Component
public class CustomResponseBodyFilter extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config) {
        return new InnerFilter();
    }

    /**
     * 定义该工厂的名称，如果不指定，则默认是去除 GatewayFilterFactory 后的名称
     * @return 自定义过滤器名称
     */
    @Override
    public String name() {
        return "CustomResponseBodyFilter";
    }

    /**
     * 过滤器的内部类
     */
    private class InnerFilter implements GatewayFilter, Ordered {
        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            try {
//                //如果出现乱码, 通过此方案解决
//                exchange.getResponse().getHeaders().set("Content-Type","application/json;charset=UTF-8");
                //获取response的 返回数据
                ServerHttpResponse originalResponse = exchange.getResponse();
                DataBufferFactory bufferFactory = originalResponse.bufferFactory();
                HttpStatus statusCode = originalResponse.getStatusCode();

                System.out.println( statusCode );
                System.out.println( statusCode == HttpStatus.OK );

                if (statusCode == HttpStatus.OK) {
                    ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
                        @Override
                        public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                            // 判断服务返回的数据类型进行拦截，根据自己的业务进行修改
                            if (APPLICATION_JSON.isCompatibleWith(getDelegate().getHeaders().getContentType())) {
                                Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                                return super.writeWith(fluxBody.buffer().map(dataBuffers -> {
                                    DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                                    DataBuffer join = dataBufferFactory.join(dataBuffers);
                                    byte[] content = new byte[join.readableByteCount()];
                                    join.read(content);
                                    DataBufferUtils.release(join);
                                    String responseData = new String(content, Charsets.UTF_8);

                                    responseData = responseData.replaceAll(":null", ":\"\"");

                                    byte[] uppedContent = responseData.getBytes(Charsets.UTF_8);
                                    //return bufferFactory.wrap(uppedContent);

                                    //响应结果集封装到实体对象中
                                    String rs = new String(uppedContent, Charset.forName("UTF-8"));
                                    return bufferFactory.wrap(JSON.toJSONBytes(new ResponseResult(statusCode.value(), statusCode.name(), JSON.parseObject(rs))));
                                }));
                            } else {
                                return chain.filter(exchange);
                            }
                        }
                    };
                    return chain.filter(exchange.mutate().response(decoratedResponse).build());
                }
                return chain.filter(exchange);
            } catch (Exception e) {
                log.error(" ReplaceNullFilter 异常", e);
                return chain.filter(exchange);
            }
        }

        @Override
        public int getOrder() {
            return -2;
        }
    }

}
