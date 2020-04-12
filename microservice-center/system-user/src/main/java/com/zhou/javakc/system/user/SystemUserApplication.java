package com.zhou.javakc.system.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 系统管理-用户管理(system-user)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing //开启JPA自动生成注册日期及修改日期
@EntityScan(basePackages = "com.zhou.javakc.common.entity") //扫描entity所在的包
@ComponentScan(basePackages = {"com.zhou.javakc.common.configuration", "com.zhou.javakc.system.user"}) //扫描配置及当前目录
public class SystemUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemUserApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
