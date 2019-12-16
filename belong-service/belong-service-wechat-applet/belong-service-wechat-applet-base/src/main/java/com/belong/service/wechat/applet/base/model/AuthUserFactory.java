package com.belong.service.wechat.applet.base.model;

import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;


/**
 * @Description: The type Auth user factory.
 * @Author: fengyu
 * @CreateDate: 2019/12/16 14:13
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
        AuthUser authUser = new AuthUser(user.getId());
        authUser.setName(user.getNickName());
        authUser.setLoginName(user.getOpenId());
        authUser.setMobile(user.getMobile());
        authUser.setEnabled(user.getEnabled());
        authUser.setOpenId(user.getOpenId());
        return authUser;
    }

}
