package com.belong.common.exception.system.user;

/**
 * @Description: 用户错误最大次数异常类
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:59
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/26 17:59
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class UserPasswordRetryLimitExceedException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordRetryLimitExceedException(int retryLimitCount) {
        super("user.password.retry.limit.exceed", new Object[]{retryLimitCount});
    }
}
