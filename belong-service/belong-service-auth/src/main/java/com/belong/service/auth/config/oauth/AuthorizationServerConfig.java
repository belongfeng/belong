package com.belong.service.auth.config.oauth;

import com.belong.common.auth.entity.BelongAuthUser;
import com.belong.service.auth.config.exception.BelongWebResponseExceptionTranslator;
import com.belong.service.auth.config.properties.AuthProperties;
import com.belong.service.auth.granter.WechatAppletCodeCustomTokenGranter;
import com.belong.service.auth.service.RedisClientDetailsService;
import com.belong.service.auth.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Classname AuthorizationServerConfig
 * @Description OAuth2认证服务器配置
 * @Date 2020/1/2 13:46
 * @Created by FengYu
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BelongWebResponseExceptionTranslator<?> belongWebResponseExceptionTranslator;
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private RedisClientDetailsService redisClientDetailsService;
    @Autowired
    private AuthProperties properties;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        System.out.println("客户端：belong-service-wechat-public   密码："+passwordEncoder().encode("belong-service-wechat-public:xiliangmen666"));
        System.out.println("客户端：belong-service-wechat-applet   密码："+passwordEncoder().encode("belong-service-wechat-applet:xiliangmen666"));
        System.out.println("客户端：belong-service-system   密码："+passwordEncoder().encode("belong-service-system:xiliangmen666"));
        //redis获取客户端方式
        clients.withClientDetails(redisClientDetailsService);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
                // 开启/oauth/check_token验证端口认证权限访问
                .tokenKeyAccess("isAuthenticated()")
                // 开启/oauth/token_key验证端口无权限访问
                .checkTokenAccess("permitAll()");
    }

    /**
     * 配置授权（authorization）以及令牌（token）的访问端点和令牌服务(token services)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .reuseRefreshTokens(false)
                .tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .exceptionTranslator(belongWebResponseExceptionTranslator);
        endpoints.accessTokenConverter(jwtAccessTokenConverter());
        List<TokenGranter> tokenGranters = getTokenGranters(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());
        tokenGranters.add(endpoints.getTokenGranter());
        endpoints.tokenGranter(new CompositeTokenGranter(tokenGranters));
    }

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("belong_" + "oauth:");
        //增加uuid是为了防止每次生成的token一样
        redisTokenStore.setAuthenticationKeyGenerator(oAuth2Authentication -> UUID.randomUUID().toString());
        return redisTokenStore;
    }

    @Bean
    @Primary
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(redisClientDetailsService);
        return tokenServices;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        DefaultAccessTokenConverter defaultAccessTokenConverter = (DefaultAccessTokenConverter) accessTokenConverter.getAccessTokenConverter();
        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        userAuthenticationConverter.setUserDetailsService(userDetailService);
        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        accessTokenConverter.setSigningKey(properties.getJwtAccessKey());
        return accessTokenConverter;
    }

    //配置多个Granters
    private List<TokenGranter> getTokenGranters(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        return new ArrayList<>(Arrays.asList(
                new WechatAppletCodeCustomTokenGranter(userDetailService, tokenServices, clientDetailsService, requestFactory)
        ));
    }

    @Bean
    public DefaultOAuth2RequestFactory oAuth2RequestFactory() {
        return new DefaultOAuth2RequestFactory(redisClientDetailsService);
    }

    /**
     * 其他登录方式增强token,客户端模式不增强
     *
     * @return TokenEnhancer
     */
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            if ("client_credentials"
                    .equals(authentication.getOAuth2Request().getGrantType())) {
                return accessToken;
            }
            DefaultOAuth2AccessToken oAuth2AccessToken = (DefaultOAuth2AccessToken) accessToken;
            BelongAuthUser belongAuthUser = (BelongAuthUser) authentication.getDetails();
            Map<String, Object> map = new LinkedHashMap<>(4);
            map.put("userId", belongAuthUser.getUserId());
            map.put("username", belongAuthUser.getUsername());
            map.put("mobile", belongAuthUser.getMobile());
            map.put("avatar", belongAuthUser.getAvatar());
            oAuth2AccessToken.setAdditionalInformation(map);
            return oAuth2AccessToken;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
