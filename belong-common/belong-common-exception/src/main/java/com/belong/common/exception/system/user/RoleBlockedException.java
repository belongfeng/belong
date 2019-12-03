package com.belong.common.exception.system.user;

/**
 * @Description: 角色锁定异常类
 * @Author: fengyu
 * @CreateDate: 2019/11/26 17:58
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/26 17:58
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class RoleBlockedException extends UserException {
    private static final long serialVersionUID = 1L;

    public RoleBlockedException() {
        super("role.blocked", null);
    }
}
