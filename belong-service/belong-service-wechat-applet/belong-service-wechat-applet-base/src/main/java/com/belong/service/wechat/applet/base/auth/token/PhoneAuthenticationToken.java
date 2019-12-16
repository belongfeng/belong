package com.belong.service.wechat.applet.base.auth.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
* @Description:    手机验证码token
* @Author:         fengyu
* @CreateDate:     2019/12/16 15:16
*/
public class PhoneAuthenticationToken extends MyAuthenticationToken {

    public PhoneAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public PhoneAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
