package com.belong.common.exception.system.user;

/**
 * @Description: 用户不存在异常类
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:59
 * @UpdateDate: 2019/11/26 17:59
 * @Version: 1.0
 */
public class UserNotExistsException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserNotExistsException() {
        super("user.not.exists", null);
    }
}
