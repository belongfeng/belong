package com.belong.common.exception.system.user;

/**
 * @Description: 用户密码不正确或不符合规范异常类
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:59
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/26 17:59
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class UserPasswordNotMatchException extends UserException {
    private static final long serialVersionUID = 1L;

    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
