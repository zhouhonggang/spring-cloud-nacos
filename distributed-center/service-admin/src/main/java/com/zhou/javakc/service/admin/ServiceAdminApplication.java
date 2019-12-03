package com.zhou.javakc.service.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 服务监控(SpringCloudAdmin)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2019-12-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class ServiceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAdminApplication.class, args);
    }

}
