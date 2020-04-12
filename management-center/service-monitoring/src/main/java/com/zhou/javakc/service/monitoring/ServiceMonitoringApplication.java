package com.zhou.javakc.service.monitoring;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 服务监控(Spring Cloud Admin)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class ServiceMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMonitoringApplication.class, args);
    }

}
