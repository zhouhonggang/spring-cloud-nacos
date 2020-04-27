package com.zhou.javakc.common.configuration.restful;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 公共组件(微服务response统一封装)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v0.2.0
 * @date 2020-02-24 18:19
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@RestControllerAdvice(basePackages = "com.zhou.javakc")
public class ResponseDataAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        // 如果类或方法有IgnoreResponseAdvice标识就不拦截
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class) ||
                (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)))
        {
            return false;
        }
        return true;
    }

    /**
     * 定义是否有异常
     */
    private boolean exceptionState;

    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object o, MethodParameter methodParameter, MediaType mediaType,
                                  Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 如果出现异常, 则按照异常内容返回
        if(exceptionState)
        {
            exceptionState = false;
            return o;
        }
        return new ResponseResult<Object>(1, 200, "\u606d\u559c\u60a8\uff0c\u6267\u884c\u6210\u529f\uff01", o);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseResult<Object> handlerException(RuntimeException exception){
        exceptionState = true;
        return new ResponseResult<Object>(-1, 500, "\u5f88\u9057\u61be\uff0c \u6267\u884c\u5931\u8d25\uff01", null);
    }

}
