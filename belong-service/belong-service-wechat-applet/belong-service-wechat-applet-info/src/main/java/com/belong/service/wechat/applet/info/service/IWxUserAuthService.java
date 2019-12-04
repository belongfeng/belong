package com.belong.service.wechat.applet.info.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;
import com.belong.service.wechat.applet.info.api.vo.WeChatRegistryUserVO;

/**
 * @Classname WxUserAuthService
 * @Description TODO
 * @Date 2019/12/4 14:45
 * @Created by FengYu
 */
public interface IWxUserAuthService {
    /**
     * 根据code 换取openid 及sessionKey
     *
     * @param code
     * @return
     */
    WxMaJscode2SessionResult wxUserLoginByCode(String code);


    /**
     * 获取微信小程序用户授权信息
     *
     * @param wxMaJscode2SessionResult
     * @return
     */
    WxUserInfoDO baseUserInfo(WxMaJscode2SessionResult wxMaJscode2SessionResult);


    /**
     * 获取微信小程序用户授权信息
     *
     * @param sessionKey
     * @return
     */
    WxUserInfoDO userInfo(String sessionKey, WeChatRegistryUserVO registryUser);
}
