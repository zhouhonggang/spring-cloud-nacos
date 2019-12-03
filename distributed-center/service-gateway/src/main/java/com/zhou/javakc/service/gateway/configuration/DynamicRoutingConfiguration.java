package com.zhou.javakc.service.gateway.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.zhou.javakc.service.gateway.entity.FilterEntity;
import com.zhou.javakc.service.gateway.entity.PredicateEntity;
import com.zhou.javakc.service.gateway.entity.RouteEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 动态网关(SpringCloudGateWay-Nacos)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2019-12-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Component
public class DynamicRoutingConfiguration implements ApplicationEventPublisherAware {

    private final Logger logger = LoggerFactory.getLogger(DynamicRoutingConfiguration.class);

    //Nacos group名称
    private static final String GROUP = "DEFAULT_GROUP";
    //Nacos data_id名称
    private static final String DATA_ID = "service-gateway-refresh.json";

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;
    private ApplicationEventPublisher applicationEventPublisher;

    @Bean
    public void refreshRouting() throws NacosException {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, "127.0.0.1:8848");
        /**
         * 如需要配置则添加
         * 639c6197-2003-44d7-9c2c-077543189142 => test
         * ede688ac-458a-4d5a-9057-68dc970e9ecc => develop
         *
         * properties.put(PropertyKeyConst.NAMESPACE, "639c6197-2003-44d7-9c2c-077543189142");
         */
        ConfigService configService = NacosFactory.createConfigService(properties);
        configService.addListener(DATA_ID, GROUP, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }
            @Override
            public void receiveConfigInfo(String configInfo) {
                logger.info(configInfo);
                boolean refreshGatewayRoute = JSONObject.parseObject(configInfo).getBoolean("refreshGatewayRoute");
                if (refreshGatewayRoute) {
                    List<RouteEntity> list = JSON.parseArray(JSONObject.parseObject(configInfo).getString("routeList")).toJavaList(RouteEntity.class);
                    for (RouteEntity route : list) {
                        update(assembleRouteDefinition(route));
                    }
                } else {
                    logger.info("路由未发生变更");
                }
            }
        });
    }

    /**
     * 动态更新路由内容
     * @param routeDefinition 路由定义
     */
    public void update(RouteDefinition routeDefinition){
        try {
            this.routeDefinitionWriter.delete(Mono.just(routeDefinition.getId()));
            logger.info("路由更新成功");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
        try {
            routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
            this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
            logger.info("路由更新成功");
        }catch (Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    public RouteDefinition assembleRouteDefinition(RouteEntity routeEntity) {
        RouteDefinition definition = new RouteDefinition();
        // 路由id
        definition.setId(routeEntity.getId());

        // 路由规则
        List<PredicateDefinition> pdList = new ArrayList<>();
        for (PredicateEntity predicateEntity: routeEntity.getPredicates()) {
            PredicateDefinition predicateDefinition = new PredicateDefinition();
            predicateDefinition.setArgs(predicateEntity.getArgs());
            predicateDefinition.setName(predicateEntity.getName());
            pdList.add(predicateDefinition);
        }
        definition.setPredicates(pdList);

        // 路由过滤器
        List<FilterDefinition> fdList = new ArrayList<>();
        for (FilterEntity filterEntity: routeEntity.getFilters()) {
            FilterDefinition filterDefinition = new FilterDefinition();
            filterDefinition.setArgs(filterEntity.getArgs());
            filterDefinition.setName(filterEntity.getName());
            fdList.add(filterDefinition);
        }
        definition.setFilters(fdList);

        // 路径路径
        URI uri = UriComponentsBuilder.fromUriString(routeEntity.getUri()).build().toUri();
        definition.setUri(uri);

        return definition;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }


}
