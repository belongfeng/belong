package com.belong.service.auth.config.oauth;

import com.belong.common.auth.entity.EndpointConstant;
import com.belong.common.auth.handler.BelongAccessDeniedHandler;
import com.belong.common.auth.handler.BelongAuthExceptionEntryPoint;
import com.belong.service.auth.config.properties.AuthProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @Classname ResourceServerConfig
 * @Description TODO
 * @Date 2020/1/2 15:14
 * @Created by FengYu
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    //@Autowired
    //private BelongAccessDeniedHandler belongAccessDeniedHandler;
    //@Autowired
    //private BelongAuthExceptionEntryPoint belongAuthExceptionEntryPoint;
    @Autowired
    private AuthProperties authProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(authProperties.getAnonUrl(), ",");

        http.csrf().disable()
                .requestMatchers().antMatchers(EndpointConstant.ALL)
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers(EndpointConstant.ALL).authenticated()
                .and().httpBasic();
    }

    //@Override
    //public void configure(ResourceServerSecurityConfigurer resources) {
    //    //resources.authenticationEntryPoint(new BelongAuthExceptionEntryPoint())
    //    //        .accessDeniedHandler(new BelongAccessDeniedHandler());
    //}
}
