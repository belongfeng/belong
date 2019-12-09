package com.belong.common.exception.wxapplet.user;

import com.belong.common.exception.wxapplet.WxAppletException;

/**
 * @Description: 微信小程序code码不匹配异常
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:46
 * @UpdateDate: 2019/11/26 17:46
 * @Version: 1.0
 */
public class WxAppletCodeMatchException extends WxAppletException {
    private static final long serialVersionUID = 1L;

    public WxAppletCodeMatchException(Object[] args) {
        super("wxapplet.user.match", args);
    }

    public WxAppletCodeMatchException() {
        super("wxapplet.user.match", null);
    }
}
