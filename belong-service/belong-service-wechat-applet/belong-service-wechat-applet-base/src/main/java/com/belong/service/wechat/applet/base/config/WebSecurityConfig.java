package com.belong.service.wechat.applet.base.config;

import com.belong.common.auth.config.AbstractWebSecurityConfig;
import com.belong.common.auth.security.LoginConstants;
import com.belong.common.core.constant.PermitAllUrl;
import com.belong.service.wechat.applet.base.service.MyUserDetailsService;
import com.belong.service.wechat.applet.base.auth.filter.CodeLoginAuthenticationFilter;
import com.belong.service.wechat.applet.base.auth.filter.PassWordLoginAuthenticationFilter;
import com.belong.service.wechat.applet.base.auth.handler.DefaultAuthenticationSuccessHandler;
import com.belong.service.wechat.applet.base.auth.handler.FormAuthenticationFailureHandler;
import com.belong.service.wechat.applet.base.auth.handler.MiniAppAuthenticationSuccessHandler;
import com.belong.service.wechat.applet.base.auth.provider.CodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description: spring-security配置
 * @Author: fengyu
 * @CreateDate: 2019/12/16 15:38
 */
@Configuration
public class WebSecurityConfig extends AbstractWebSecurityConfig {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private DefaultAuthenticationSuccessHandler defaultAuthenticationSuccessHandler;
    @Autowired
    private FormAuthenticationFailureHandler formAuthenticationFailureHandler;
    @Autowired
    private MiniAppAuthenticationSuccessHandler miniAppAuthenticationSuccessHandler;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * http请求
     *
     * @param security
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .addFilterBefore(getCodeLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(getMyLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/**/wxUserAuth/**", "/**/db/**").permitAll()
                .antMatchers(HttpMethod.POST, LoginConstants.MINI_APP_LOGIN).permitAll();
        super.configure(security);
    }

    /**
     * web资源请求
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略权限校验的访问路径
        web.ignoring().antMatchers(PermitAllUrl.permitAllUrl("/upload/file", "/upload/**"));
    }


    /**
     * 用户验证
     *
     * @param auth
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.authenticationProvider(codeAuthenticationProvider());
    }

    @Bean
    public BCryptPasswordEncoder myEncoder() {
        return new BCryptPasswordEncoder(6);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(myUserDetailsService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(myEncoder());
        return provider;
    }

    @Bean
    public CodeAuthenticationProvider codeAuthenticationProvider() {
        CodeAuthenticationProvider provider = new CodeAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(myUserDetailsService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    /**
     * 方法实现说明:通过账号密码登录拦截器
     *
     * @param
     * @return com.belong.service.wechat.applet.base.auth.filter.PassWordLoginAuthenticationFilter
     * @throws
     * @author belongfeng
     */
    @Bean
    public PassWordLoginAuthenticationFilter getMyLoginAuthenticationFilter() {
        PassWordLoginAuthenticationFilter filter = new PassWordLoginAuthenticationFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置成功处理响应
        filter.setAuthenticationSuccessHandler(defaultAuthenticationSuccessHandler);
        //设置失败处理响应
        filter.setAuthenticationFailureHandler(formAuthenticationFailureHandler);
        return filter;
    }

    /**
     * 方法实现说明: 小程序通过code登录拦截器
     *
     * @param
     * @return com.belong.service.wechat.applet.base.auth.filter.CodeLoginAuthenticationFilter
     * @throws
     * @author belongfeng
     */
    @Bean
    public CodeLoginAuthenticationFilter getCodeLoginAuthenticationFilter() {
        CodeLoginAuthenticationFilter filter = new CodeLoginAuthenticationFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        filter.setAuthenticationSuccessHandler(miniAppAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(formAuthenticationFailureHandler);
        return filter;
    }
}
