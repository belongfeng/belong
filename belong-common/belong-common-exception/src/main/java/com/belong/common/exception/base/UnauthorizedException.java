package com.belong.common.exception.base;

/**
 * @Description: 无权限异常
 * @Author: fengyu
 * @CreateDate: 2019/11/27 9:48
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/27 9:48
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class UnauthorizedException extends RuntimeException {
    private static final String DEFAULT_MSG = "unauthorized";

    private static final long serialVersionUID = 3885400551304383736L;

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super(DEFAULT_MSG);
    }
}