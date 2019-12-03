# spring-cloud-nacos
基于Alibaba Nacos构建云原生应用的动态服务发现、配置管理和服务管理平台!
![Alt text](nacos.jpg)

##环境运行步骤
    1. 启动配置nacos [ nacos ](https://github.com/alibaba/nacos/releases "下载地址")
        1.1: 进入到 ~/nacos/bin/目录
            linux: startup.sh
            windows: shutdown.bat
        1.2: 访问 http://ip:8848/nacos
            账号: nacos
            密码: nacos
        1.3: cmd中输入    
            curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=test-module.properties&group=DEFAULT_GROUP&content=user.id=1001%0Auser.name=administrator%0Auser.age=21"
  
    2. 启动distributed-center/service-admin
    3. 启动distributed-center/service-gateway
    4. 启动
        distributed-service/test-module/TestModuleApplication (18101)
        distributed-service/test-module/TestModuleApplication --spring.profile.active=test (18102)
        distributed-service/test-module/TestModuleApplication --spring.profile.active=develop (18103)