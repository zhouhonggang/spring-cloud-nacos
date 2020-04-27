package com.zhou.javakc.common.configuration.restful;

import lombok.Data;

/**
 * 公共组件(封装restful结果集)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Data
public class ResponseResult<T> {

    /**
     * 执行状态标识请求成功与否；
     * 如 [1:成功；-1:失败]
     */
    private int status = 1;

    /**
     * 错误码，给出明确错误码，更好的应对业务异常；
     * 请求成功该值可为空
     */
    private int code;

    /**
     * 错误消息，与错误码相对应，更具体的描述异常信息
     */
    private String message = "";

    /**
     * 返回结果，通常是 Bean 对象对应的 JSON 数据；
     * 通常为了应对不同返回值类型，将其声明为泛型类型
     */
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(T data) {
        this.data = data;
    }

    public ResponseResult(int status, int code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
