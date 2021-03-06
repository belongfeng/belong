package com.belong.service.wechat.applet.base.auth.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/16 15:16
*/
public class CodeAuthenticationToken extends MyAuthenticationToken {

    private static final long serialVersionUID = 420L;

    public CodeAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public CodeAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
