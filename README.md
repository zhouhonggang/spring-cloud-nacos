# spring-cloud-nacos

### 构建云原生应用的动态服务发现、配置管理和服务管理平台!



### 项目架构

```
    spring-cloud-nacos
    ├──management-center(spring-cloud微服务管理中心)
    │   ├── service-monitoring(服务监控)
    │   ├── security-oauth2(服务鉴权)
    │   └── gateway-forwarding(服务转发)
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
```



### 环境搭建

> 1. 导入~schema/*.sql到mysql数据库(v5.6+)
>
> 2. 配置启动nacos
>
>    > 2.1 修改~/config/application.properties
>    >
>    > #### 	添加mysql连接配置
>    > ​	spring.datasource.platform=mysql
>    > ​	db.num=1
>    > ​	db.url.0=jdbc:mysql://127.0.0.1:3306/nacos_config?	characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
>    > ​	db.user=root
>    > ​	db.password=123456
>    >
>    > ####    启动nacos服务
>    >
>    > 2.2 启动 ~bin/stratup.cmd
>    >
>    > 2.3 访问 http://127.0.0.1:8848/nacos
>
> 3. 依次启动服务
>
>    > 3.1 service-monitoring
>    >
>    > 3.2 security-oauth2
>    >
>    > 3.3 gateway-forwarding