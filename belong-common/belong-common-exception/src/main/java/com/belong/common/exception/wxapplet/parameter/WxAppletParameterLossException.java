package com.belong.common.exception.wxapplet.parameter;

import com.belong.common.exception.wxapplet.WxAppletException;

/**
 * @Description: 微信小程序参数丢失
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:46
 * @UpdateDate: 2019/11/26 17:46
 * @Version: 1.0
 */
public class WxAppletParameterLossException extends WxAppletException {
    private static final long serialVersionUID = 1L;

    public WxAppletParameterLossException(Object[] args) {
        super("wxapplet.parameter.definite.loss", args);
    }

    public WxAppletParameterLossException() {
        super("wxapplet.parameter.loss", null);
    }
}
