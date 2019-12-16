package com.belong.service.wechat.applet.info.serviceImpl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaTemplateMessage;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.exception.wxapplet.login.MiniAppLoginException;
import com.belong.common.exception.wxapplet.parameter.WxAppletParameterIllegalException;
import com.belong.common.exception.wxapplet.parameter.WxAppletParameterLossException;
import com.belong.common.exception.wxapplet.request.WxappletrequestException;
import com.belong.service.wechat.applet.casus.api.feign.RemoteWxUserCasusDOFService;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;
import com.belong.service.wechat.applet.info.api.vo.WeChatRegistryUserVO;
import com.belong.service.wechat.applet.info.service.IWxUserAuthService;
import com.belong.service.wechat.applet.info.service.IWxUserInfoService;
import com.codingapi.tx.annotation.TxTransaction;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private RemoteWxUserCasusDOFService remoteWxUserCasusDOFService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * @Description:
     * @Author: fengyu
     * @CreateDate: 2019/12/4 15:05
     * @UpdateDate: 2019/12/4 15:05
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
     * @UpdateDate: 2019/12/4 15:23
     * @Version: 1.0
     */
    @Override
    public WxUserInfoDO baseUserInfo(WxMaJscode2SessionResult wxMaJscode2SessionResult) {
        if (com.belong.common.util.StringUtils.isNull(wxMaJscode2SessionResult)) {
            throw new MiniAppLoginException("用户信息校验失败");
        }
        try {
            WxUserInfoDO wxUserInfoDO = wxUserInfoService.getOne(new QueryWrapper<>(WxUserInfoDO.builder().openId(wxMaJscode2SessionResult.getOpenid()).build()));
            //判断用户是否登录过
            if (com.belong.common.util.StringUtils.isNull(wxUserInfoDO)) {
                wxUserInfoDO = new WxUserInfoDO();
                wxUserInfoDO.setAppId(wxMaService.getWxMaConfig().getAppid());
                wxUserInfoDO.setEnabled(true);
                wxUserInfoDO.setUnionId(wxMaJscode2SessionResult.getUnionid());
                wxUserInfoDO.setOpenId(wxMaJscode2SessionResult.getOpenid());
            }
            wxUserInfoDO.setLastLoginTime(new Date());
            wxUserInfoService.saveOrUpdate(wxUserInfoDO);
            redisTemplate.opsForValue().set(WxUserInfoDO.SESSION_KEY + wxMaJscode2SessionResult.getOpenid(), wxMaJscode2SessionResult.getSessionKey());
            return wxUserInfoDO;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new MiniAppLoginException("用户信息校验失败");
        }
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

    @TxTransaction(isStart = true)
    @Transactional
    @Override
    public Boolean tetLcn(String oneId, String twoId) {
        ResponseVO re = remoteWxUserCasusDOFService.remove(twoId);
        if (re.getCode() == 200) {
            //wxUserInfoService.removeById(oneId);
            //throw new RuntimeException("事务失败");
            return wxUserInfoService.removeById(oneId);
        }
        return false;
    }
}
