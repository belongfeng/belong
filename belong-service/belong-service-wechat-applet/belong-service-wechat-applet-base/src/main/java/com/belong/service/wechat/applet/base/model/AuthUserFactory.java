package com.belong.service.wechat.applet.base.model;

import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;


/**
 * @Description: The type Auth user factory.
 * @Author: fengyu
 * @CreateDate: 2019/12/3 15:30
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/12/3 15:30
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public final class AuthUserFactory {

    private AuthUserFactory() {
    }

    /**
     * 方法实现说明:   user.getTokenPwd()
     *
     * @param user user the user
     * @return com.belong.service.wechat.applet.base.model.AuthUser
     * @throws
     * @author fengyu
     * @date 2019/12/3 17:33
     */
    public static AuthUser create(WxUserInfoVO user) {
        return new AuthUser(
                user.getId(),
                user.getOpenId(),
                user.getUnionId(),
                user.getTokenPwd(),
                user.getEnabled()

        );
    }

}
