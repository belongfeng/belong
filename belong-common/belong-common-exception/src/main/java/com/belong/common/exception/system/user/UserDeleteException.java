package com.belong.common.exception.system.user;

/**
 * @Description: 用户账号已被删除
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:59
 * @UpdateDate: 2019/11/26 17:59
 * @Version: 1.0
 */
public class UserDeleteException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserDeleteException() {
        super("user.password.delete", null);
    }
}
