package com.zhou.javakc.gateway.forwarding.entity.response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 公共组件(封装restful结果集)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Data
@Accessors(chain = true)
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 5005318709330004756L;

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

    /**
     * 执行成功
     * @param code http状态码
     * @param message http状态消息
     * @param data 返回json数据
     * @param <T> 数据类型
     * @return 成功JSON
     */
    public static <T> ResponseResult<T> success(int code, String message, T data) {
        return new ResponseResult<T>(1, code, message, data);
    }

    /**
     * 执行失败
     * @param code http状态码
     * @param message http状态消息
     * @param <T> 数据类型
     * @return 失败JSON
     */
    public static <T> ResponseResult<T> error(int code, String message) {
        return new ResponseResult<T>(-1, code, message, null);
    }

    public ResponseResult(int status, int code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public ResponseResult(int status, int code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
