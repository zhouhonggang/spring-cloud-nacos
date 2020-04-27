package com.zhou.javakc.system.role;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 系统管理-角色管理(system-role)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
@EntityScan(basePackages = "com.zhou.javakc.common.entity")
@ComponentScan(basePackages = {"com.zhou.javakc.common.configuration", "com.zhou.javakc.system.role"})
public class SystemRoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemRoleApplication.class, args);
    }

}
