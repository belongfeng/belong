package com.belong.eureka.security;


import lombok.SneakyThrows;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description: 用于服务注册时候进行鉴权
 * @Author: fengyu
 * @CreateDate: 2019/10/28 16:34
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/10/28 16:34
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest()
                .authenticated().and().httpBasic();
    }
}
