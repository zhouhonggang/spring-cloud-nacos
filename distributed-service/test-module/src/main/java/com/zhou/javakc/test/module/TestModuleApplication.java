package com.zhou.javakc.test.module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 测试模块
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2019-12-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TestModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestModuleApplication.class, args);
    }

}
