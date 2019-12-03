package com.zhou.javakc.service.gateway.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 网关路由过滤器
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2019-12-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Getter
@Setter
public class FilterEntity {
    //路由名称
    private String name;
    //路由规则
    private Map<String, String> args = new LinkedHashMap<>();
}
