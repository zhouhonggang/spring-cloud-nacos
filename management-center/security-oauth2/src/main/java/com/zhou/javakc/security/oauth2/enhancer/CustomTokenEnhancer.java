package com.zhou.javakc.security.oauth2.enhancer;

import com.zhou.javakc.security.oauth2.entity.SystemUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        // 获取当前登录用户信息
        SystemUser user = (SystemUser)oAuth2Authentication.getUserAuthentication().getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>(8);
        // 定义客户端接收到用户的信息
        additionalInfo.put("user_id", user.getUserid());
        additionalInfo.put("user_name", user.getUsername());
        additionalInfo.put("nick_name", user.getNickname());
        additionalInfo.put("user_photo", user.getUserphoto());
        // 向令牌中追加用户信息
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }

}
