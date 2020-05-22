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
    │   ├── system-role(系统管理-角色管理)
    │   ├── system-user(系统管理-用户管理)
    │──common-components(项目公共组件)
    │    ├── common-entity(公共实体对象)
    │    ├── common-mybatis(mybatis底层封装)
    │    ├── common-datajpa(spring data jpa底层封装)
    │    └── common-redis(NoSQL底层API封装)
    └──schema(建表语句)
        ├── spring_nacos_config.sql(alibaba nacos)
        ├── spring_cloud_oauth2.sql(oauth2 认证)
        └── spring_cloud_nacos.sql(微服务模块)
```

### 环境搭建
> 1. **导入~schema目录中所有sql文件到mysql数据库(v5.6+)**
> 2. **配置启动nacos**
>    > 2.1 **编辑** 向**nacos/config/application.properties**中添加如下代码
>    > 
>    > ​	spring.datasource.platform=mysql
>    > ​	db.num=1
>    > ​	db.url.0=jdbc:mysql://**地址**:3306/spring_nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
>    > ​	db.user=**账号**
>    > ​	db.password=**密码**
>    > 
>    >2.2 **启动**  ~bin/stratup.cmd
>    > 
>    >2.3 **访问**  http://127.0.0.1:8000/nacos
>    > 
>    >2.4 **账号** **nacos** 密码:**nacos**
>    
> 3. 配置nacos项目信息  <配置管理-配置列表>
>    > 3.1  data-source-microservice.yaml 
>    >
>    > > 修改数据库连接
>    >
>    > 3.2  data-source-oauth2.yaml
>    >
>    > > 修改数据库连接
>    >
>    > 3.3  spring-cloud-nacos.yaml 
>    >
>    > > 修改本地文件存储路径