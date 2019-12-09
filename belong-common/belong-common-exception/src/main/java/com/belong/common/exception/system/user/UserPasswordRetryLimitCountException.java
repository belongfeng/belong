package com.belong.common.exception.system.user;

/**
 * @Description: 用户错误记数异常类
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:59
 * @UpdateDate: 2019/11/26 17:59
 * @Version: 1.0
 */
public class UserPasswordRetryLimitCountException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitCountException(int retryLimitCount) {
        super("user.password.retry.limit.count", new Object[]{retryLimitCount});
    }
}
