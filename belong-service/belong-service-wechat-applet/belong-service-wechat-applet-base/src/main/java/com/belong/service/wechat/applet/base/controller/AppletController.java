package com.belong.service.wechat.applet.base.controller;

import com.belong.common.auth.security.AuthenticationTokenFilter;
import com.belong.common.core.base.BaseController;
import com.belong.common.util.StringUtils;
import com.belong.service.wechat.applet.base.utils.TokenUtil;
import com.belong.service.wechat.applet.info.api.feign.RemoteWxUserInfoDOService;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import static com.belong.common.util.ServletUtils.getRequest;

/**
 * @Classname appletController
 * @Description TODO
 * @Date 2019/12/4 17:08
 * @Created by FengYu
 */
public class AppletController extends BaseController {
    @Autowired
    protected TokenUtil tokenUtil;
    /**
     * 用户服务
     */
    @Autowired
    protected RemoteWxUserInfoDOService remoteWxUserInfoDOService;

    public String getUserId() {
        return getUserInfo().getId();
    }

    public WxUserInfoVO getUserInfo() {
        String tokenHeader = getRequest().getHeader(AuthenticationTokenFilter.TOKEN_HEADER);
        UserDetails userDetails = tokenUtil.getUserDetails(tokenHeader);
        WxUserInfoVO user = remoteWxUserInfoDOService.getWxUserInfoByOpenId(userDetails.getUsername()).getData();
        return user;
    }
}
