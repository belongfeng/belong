package com.belong.common.exception.wxapplet.parameter;

import com.belong.common.exception.wxapplet.WxAppletException;

/**
 * @Description: 微信小程序参数不合法异常
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:46
 * @UpdateDate: 2019/11/26 17:46
 * @Version: 1.0
 */
public class WxAppletParameterIllegalException extends WxAppletException {
    private static final long serialVersionUID = 1L;

    public WxAppletParameterIllegalException(Object[] args) {
        super("wxapplet.parameter.definite.illegal", args);
    }

    public WxAppletParameterIllegalException() {
        super("wxapplet.parameter.illegal", null);
    }
}
