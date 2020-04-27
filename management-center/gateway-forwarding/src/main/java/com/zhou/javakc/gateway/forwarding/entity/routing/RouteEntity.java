package com.zhou.javakc.gateway.forwarding.entity.routing;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态路由实体类
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2020-3-8 09:12
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Data
public class RouteEntity {
    //路由id
    private String id;
    //路由转发路径
    private String uri;
    //路由执行的顺序
    private int order = 0;
    //路由断言集合
    private List<PredicateEntity> predicates = new ArrayList<>();
    //路由过滤器集合
    private List<FilterEntity> filters = new ArrayList<>();
}
