package com.belong.common.exception.system.user;

/**
 * @Description: 验证码错误异常类
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:55
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/26 17:55
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class CaptchaException extends UserException {
    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super("user.jcaptcha.error", null);
    }
}
