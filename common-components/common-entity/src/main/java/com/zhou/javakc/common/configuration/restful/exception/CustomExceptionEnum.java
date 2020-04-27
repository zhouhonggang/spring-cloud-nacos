package com.zhou.javakc.common.configuration.restful.exception;

/**
 * 公共组件(自定义错误封装)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-24 18:19
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
public enum CustomExceptionEnum {

    UNKNOWN_EXCEPTION(1000,"未知异常"),
    PARAMETER_INVALID(1001,"参数不合法"),

    USER_NOT_EXIST(2001,"用户不存在"),
    USER_NOT_LOGIN(2002,"用户未登录"),
    USER_LOGIN_FAIL(2003,"账号或密码错误"),
    ;

    CustomExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int state = -1;
    private int code;
    private String message;

    public int getState() {
        return state;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
