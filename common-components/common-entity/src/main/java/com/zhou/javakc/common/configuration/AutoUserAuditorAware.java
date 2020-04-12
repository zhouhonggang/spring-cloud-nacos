package com.zhou.javakc.common.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 公共组件(自动保存 添加人/修改人)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Configuration
public class AutoUserAuditorAware implements AuditorAware<String> {

    /** 获取当前登陆的信息 */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable("1");
    }

}
