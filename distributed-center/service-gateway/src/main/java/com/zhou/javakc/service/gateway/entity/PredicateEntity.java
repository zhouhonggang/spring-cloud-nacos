package com.zhou.javakc.service.gateway.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 网关路由断言
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2019-12-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Getter
@Setter
public class PredicateEntity {
    //断言名称
    private String name;
    //断言规则
    private Map<String, String> args = new LinkedHashMap<>();
}
