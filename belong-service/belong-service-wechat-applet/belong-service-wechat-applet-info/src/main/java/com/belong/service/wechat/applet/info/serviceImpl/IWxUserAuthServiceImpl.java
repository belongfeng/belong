package com.belong.service.wechat.applet.info.serviceImpl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.belong.common.exception.wxapplet.parameter.WxAppletParameterIllegalException;
import com.belong.common.exception.wxapplet.parameter.WxAppletParameterLossException;
import com.belong.common.exception.wxapplet.request.WxappletrequestException;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;
import com.belong.service.wechat.applet.info.api.vo.WeChatRegistryUserVO;
import com.belong.service.wechat.applet.info.service.IWxUserAuthService;
import com.belong.service.wechat.applet.info.service.IWxUserInfoService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Classname IWxUserAuthServiceImpl
 * @Description TODO
 * @Date 2019/12/4 15:05
 * @Created by FengYu
 */
@Service
@Slf4j
public class IWxUserAuthServiceImpl implements IWxUserAuthService {

    @Autowired
    private WxMaService wxMaService;
    @Autowired
    private IWxUserInfoService wxUserInfoService;

    /**
     * @Description:
     * @Author: fengyu
     * @CreateDate: 2019/12/4 15:05
     * @UpdateUser: fengyu
     * @UpdateDate: 2019/12/4 15:05
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    @Override
    public WxMaJscode2SessionResult wxUserLoginByCode(String code) {
        if (StringUtils.isBlank(code)) {
            throw new WxAppletParameterLossException(new String[]{"code"});
        }
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            log.info(session.getSessionKey());
            log.info(session.getOpenid());
            return session;
            //TODO 可以增加自己的逻辑，关联业务相关数据
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw new WxAppletParameterIllegalException(new String[]{"code"});
        }
    }

    /**
     * @Description:
     * @Author: fengyu
     * @CreateDate: 2019/12/4 15:23
     * @UpdateUser: fengyu
     * @UpdateDate: 2019/12/4 15:23
     * @UpdateRemark: 修改内容
     * @Version: 1.0
     */
    @Override
    public WxUserInfoDO baseUserInfo(WxMaJscode2SessionResult wxMaJscode2SessionResult) {
        Date now = new Date();
        WxUserInfoDO wxUserInfoDO = wxUserInfoService.getOne(new QueryWrapper<>(WxUserInfoDO.builder().openId(wxMaJscode2SessionResult.getOpenid()).build()));
        if (com.belong.common.util.StringUtils.isNull(wxUserInfoDO)) {
            wxUserInfoDO = new WxUserInfoDO();
        }
        wxUserInfoDO.setLastLoginTime(now);
        wxUserInfoDO.setEnabled(true);
        wxUserInfoDO.setDelFlag("0");
        wxUserInfoDO.setOpenId(wxMaJscode2SessionResult.getOpenid());
        wxUserInfoService.saveOrUpdate(wxUserInfoDO);
        return wxUserInfoDO;
    }

    @Override
    public WxUserInfoDO userInfo(String sessionKey, WeChatRegistryUserVO registryUser) {
        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(sessionKey, registryUser.getRawData(), registryUser.getSignature())) {
            throw new WxAppletParameterLossException();
        }
        // 解密用户信息
        WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionKey, registryUser.getEncryptedData(), registryUser.getIv());
        WxUserInfoDO wxUserInfoDO = wxUserInfoService.getOne(new QueryWrapper<>(WxUserInfoDO.builder().openId(userInfo.getOpenId()).build()));
        if (com.belong.common.util.StringUtils.isNull(wxUserInfoDO)) {
            wxUserInfoDO = new WxUserInfoDO();
        }
        wxUserInfoDO.setLastLoginTime(new Date());
        wxUserInfoDO.setEnabled(true);
        wxUserInfoDO.setDelFlag("0");
        wxUserInfoDO.setNickName(userInfo.getNickName());
        wxUserInfoDO.setOpenId(userInfo.getOpenId());
        wxUserInfoService.saveOrUpdate(wxUserInfoDO);
        return wxUserInfoDO;
    }

    @Override
    public WxMaPhoneNumberInfo userPhone(String appid, String sessionKey, WeChatRegistryUserVO registryUser) {
        // 用户信息校验
        if (!wxMaService.getUserService().checkUserInfo(sessionKey, registryUser.getRawData(), registryUser.getSignature())) {
            throw new WxAppletParameterIllegalException();
        }
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(sessionKey, registryUser.getEncryptedData(), registryUser.getIv());
        return phoneNoInfo;
    }

    @Override
    public String getAccessToken(String appid) {
        try {
            return wxMaService.getAccessToken();
        } catch (WxErrorException e) {
            throw new WxappletrequestException("获取AccessToken失败");
        }
    }

    @Override
    public void sendWxMaTemplateMessage(String appid, WxMaTemplateMessage wxMaTemplateMessage) throws WxErrorException {
        wxMaService.getMsgService().sendTemplateMsg(wxMaTemplateMessage);
    }
}
