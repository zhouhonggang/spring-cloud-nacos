#spring-cloud-nacos

##项目架构
    spring-cloud-nacos(项目root)
    ├──management-center(spring-cloud微服务管理中心)
    │   ├── service-monitoring(服务监控)
    │   ├── gateway-forwarding(服务转发)
    ├──microservice-center(spring-boot微服务模块中心)
    │   ├── system-user(系统管理-用户管理)
    │──common-components(项目公共组件)
    │    ├── common-entity(公共实体对象)
    │    ├── common-mybatis(mybatis底层封装)
    │    ├── common-datajpa(spring data jpa底层封装)
    │    └──common-redis(NoSQL底层API封装)
    └──schema(建表语句)
        ├──nacos_mysql.sql(alibaba nacos)
        ├──security_oauth2.sql(oauth2 认证)
        └──spring_cloud_nacos.sql(微服务模块)
##
    
##
    