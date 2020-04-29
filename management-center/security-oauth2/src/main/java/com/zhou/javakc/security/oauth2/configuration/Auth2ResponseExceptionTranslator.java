package com.zhou.javakc.security.oauth2.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 自定义登陆错误信息(Spring Cloud Security Oauth2)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2019-12-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Component
public class Auth2ResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {

    private Map<String, Object> data = new HashMap<>();

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) {

        data.put("status", -1);
        data.put("data", null);

        // 账号错误
        if(e instanceof InternalAuthenticationServiceException)
        {
            data.put("code", 1001);
            data.put("message", "\u8d26\u53f7\u586b\u5199\u9519\u8bef\uff01");
        }
        // 密码错误
        else if(e instanceof InvalidGrantException)
        {
            data.put("code", 1002);
            data.put("message", "\u5bc6\u7801\u586b\u5199\u9519\u8bef\uff01");
        }
        // 没有携带 grant_type
        else if(e instanceof InvalidRequestException)
        {
            data.put("code", 1003);
            data.put("message", "\u672a\u643a\u5e26\u0067\u0072\u0061\u006e\u0074\u005f\u0074\u0079\u0070\u0065\uff01");
        }
        // 其他错误
        else
        {
            data.put("code", 1004);
            data.put("message", "\u5176\u4ed6\u767b\u5f55\u9519\u8bef\uff01");
        }
        return new ResponseEntity<>(valueOf(data), HttpStatus.BAD_REQUEST);
    }

    public static OAuth2Exception valueOf(Map<String, Object> errorParams) {
        OAuth2Exception ex = new OAuth2Exception("BAD_REQUEST");
        Set<Map.Entry<String, Object>> entries = errorParams.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            ex.addAdditionalInformation(key, entry.getValue()+"");
        }
        return ex;
    }

}
