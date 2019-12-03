package com.belong.common.exception.system.user;

import com.belong.common.exception.base.BaseException;

/**
 * @Description: 用户信息异常类
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:55
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/26 17:55
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
