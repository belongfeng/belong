package com.belong.service.wechat.applet.info.serviceImpl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.belong.common.exception.wxapplet.WxAppletException;
import com.belong.common.exception.wxapplet.parameter.WxAppletParameterLossException;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;
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
            throw new WxAppletParameterLossException();
        }
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            log.info(session.getSessionKey());
            log.info(session.getOpenid());
            return session;
            //TODO 可以增加自己的逻辑，关联业务相关数据
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw new WxAppletParameterLossException();
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
        wxUserInfoService.save(wxUserInfoDO);
        return wxUserInfoDO;
    }
}
