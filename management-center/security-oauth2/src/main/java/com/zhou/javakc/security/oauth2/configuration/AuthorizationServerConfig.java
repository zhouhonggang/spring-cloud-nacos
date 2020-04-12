package com.zhou.javakc.security.oauth2.configuration;

import com.zhou.javakc.security.oauth2.enhancer.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * 启用安全认证(Spring Cloud Security Oauth2)
 * @project spring-cloud-nacos
 * @author zhou
 * @version v1.0.0
 * @date 2019-12-03 10:24
 * @copyright Copyright (c) 2019, www.javakc.com All Rights Reserved.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 使用redis缓存token
     * @return RedisTokenStore
     */
    @Bean
    public RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 使用jdbc验证用户信息
     * @return JdbcClientDetailsService
     */
    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    /**
     * 配置默认token服务
     * @return DefaultTokenServices
     */
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        //采用redis管理token
        defaultTokenServices.setTokenStore(redisTokenStore());
        //是否支持刷新token
        defaultTokenServices.setSupportRefreshToken(true);
        //是否支持复用token
        defaultTokenServices.setReuseRefreshToken(true);
        //token有效期 默认12小时
        defaultTokenServices.setAccessTokenValiditySeconds(60*60*6);
        //token刷新有效期 默认30天
        defaultTokenServices.setRefreshTokenValiditySeconds(60*60*24*30);
        //token令牌携带 用户信息
        defaultTokenServices.setTokenEnhancer(tokenEnhancer());
        return defaultTokenServices;
    }

    /**
     * 客户端相关配置
     * @param clients 客户端
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 通过数据库来存储调取详情信息
        clients.withClientDetails(jdbcClientDetailsService());
    }

    /**
     * 认证服务终端配置
     * 配置授权（authorization）及令牌（token）的访问端点和令牌服务(token services)
     * @param endpoints 终端
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            .tokenServices(defaultTokenServices())
            .authenticationManager(authenticationManager)
            .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE);
    }

    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束
     * @param oauthServer AuthorizationServerSecurityConfigurer
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                // 对于/oauth/token支持client_id以及client_secret作登录认证
                .allowFormAuthenticationForClients();
    }

    @Bean
    @Primary
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

}
