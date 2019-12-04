package com.belong.service.wechat.applet.base.config;

import com.belong.common.auth.config.AbstractWebSecurityConfig;
import com.belong.common.core.constant.PermitAllUrl;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

/**
 * @Description: spring-security配置
 * @Author: fengyu
 * @CreateDate: 2019/12/4 16:03
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/12/4 16:03
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Configuration
@Order(-50)
public class WebSecurityConfig extends AbstractWebSecurityConfig {
    /**
     * @Description: 放过的资源接口请求路径，将不在验证关键字 Authorization
     * @Author: fengyu
     * @CreateDate: 2019/12/4 16:11
     * @UpdateUser: fengyu
     * @UpdateDate: 2019/12/4 16:11
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //忽略权限校验的访问路径
        web.ignoring()
                .antMatchers(PermitAllUrl.permitAllUrl("/**/wxUserAuth/**","/**/db/**"));
                //.antMatchers(HttpMethod.POST, "/*/user");
    }

    /**
     * 方法实现说明: 放过的http接口请求路径，将不在验证关键字 Authorization
     *
     * @param security
     * @return void
     * @throws
     * @author fengyu
     * @date 2019/12/4 16:09
     */
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/**/wxUserAuth/**","/**/db/**").permitAll();
        //.antMatchers(HttpMethod.POST, "/*/authLogin/token").permitAll();
        super.configure(security);
    }
}
