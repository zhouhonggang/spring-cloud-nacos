package com.zhou.javakc.gateway.forwarding.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ResponseResult {
    /** 响应状态码 */
    private Integer code;
    /** 响应状态消息 */
    private String message;
    /** 响应内容 */
    private Map<String, Object> data;
}
