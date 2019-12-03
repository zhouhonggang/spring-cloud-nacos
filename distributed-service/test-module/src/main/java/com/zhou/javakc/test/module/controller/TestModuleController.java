package com.zhou.javakc.test.module.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试模块-Controller
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2019-12-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@RefreshScope
@RestController
@RequestMapping("test-module")
public class TestModuleController {

    @Value("${user.name}")
    private String userName;

    @GetMapping("echo")
    public String echo()
    {
        System.out.println("test module controller echo method!");
        return "hello world:"+userName;
    }

}
