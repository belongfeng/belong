package com.belong.common.exception.wxapplet.login;

import org.springframework.security.core.AuthenticationException;

/**
 * @author lvhaibao
 * @description
 * @date 2019/1/2 0002 10:45
 */
public class MiniAppLoginException extends AuthenticationException {
    public MiniAppLoginException(String msg) {
        super(msg);
    }
}
