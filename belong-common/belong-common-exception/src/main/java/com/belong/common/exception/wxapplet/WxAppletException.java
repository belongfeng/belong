package com.belong.common.exception.wxapplet;


import com.belong.common.exception.base.BaseException;

/**
 * @Description: 微信小程序根异常
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:43
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/26 17:43
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class WxAppletException extends BaseException {
    private static final long serialVersionUID = 1L;

    public WxAppletException(String code, Object[] args) {
        super("wxapplet", code, args, null);
    }
}
