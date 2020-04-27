package com.zhou.javakc.gateway.forwarding.entity.routing;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 动态路由实体类
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2020-3-8 09:12
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Data
public class FilterEntity {
    //路由名称
    private String name;
    //路由规则
    private Map<String, String> args = new LinkedHashMap<>();
}
