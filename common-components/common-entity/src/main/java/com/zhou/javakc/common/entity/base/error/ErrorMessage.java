package com.zhou.javakc.common.entity.base.error;

import lombok.Getter;
import lombok.Setter;

/**
 * 公共组件(校验提示信息)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Getter
@Setter
public class ErrorMessage {
    /** 校验字段名称*/
    private String field;
    /** 校验对象名称*/
    private String objectName;
    /** 校验提示信息*/
    private String message;
}
