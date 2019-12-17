package com.belong.service.wechat.applet.info.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;
import com.belong.service.wechat.applet.info.api.vo.WeChatRegistryUserVO;
import me.chanjar.weixin.common.error.WxErrorException;

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

    /**
     * 微信用户绑定的手机号相关信息
     *
     * @param sessionKey
     * @param registryUser
     * @return
     */
    WxMaPhoneNumberInfo userPhone(String openId,String sessionKey, WeChatRegistryUserVO registryUser);

    /**
     * 获取微信小程序接口调用凭证
     *
     * @return
     */
    String getAccessToken(String appid);

    /**
     * 发送微信小程序模板消息
     *
     * @return
     */
    void sendWxMaTemplateMessage(String appid, WxMaTemplateMessage wxMaTemplateMessage) throws WxErrorException;

    /**
     * 测试lcn事务
     *
     * @return
     */
    Boolean tetLcn(String oneId,String twoId);

}
