package com.zhou.javakc.service.gateway.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 网关路由
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2019-12-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Getter
@Setter
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
