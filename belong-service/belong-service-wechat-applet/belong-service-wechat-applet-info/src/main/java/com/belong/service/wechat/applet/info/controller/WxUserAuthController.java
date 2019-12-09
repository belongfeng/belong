package com.belong.service.wechat.applet.info.controller;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.belong.common.auth.util.WebUtils;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.exception.wxapplet.parameter.WxAppletParameterLossException;
import com.belong.service.wechat.applet.base.controller.AppletController;
import com.belong.service.wechat.applet.base.model.AuthUser;
import com.belong.service.wechat.applet.base.utils.TokenUtil;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;
import com.belong.service.wechat.applet.info.api.vo.WeChatAppletLoginResultVO;
import com.belong.service.wechat.applet.info.api.vo.WeChatRegistryUserVO;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import com.belong.service.wechat.applet.info.service.IWxUserAuthService;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 微信小程序登录
 * @Author: fengyu
 * @CreateDate: 2019/12/4 14:27
 * @UpdateDate: 2019/12/4 14:27
 * @Version: 1.0
 */
@Api(tags = "微信用户登录")
@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/wxUserAuth")
@Slf4j
public class WxUserAuthController extends AppletController {
    @Autowired
    private final IWxUserAuthService wxUserAuthService;
    /**
     * 权限管理
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 方法实现说明:当仅获取code时得登录
     *
     * @param code
     * @return com.belong.common.core.base.ResponseVO<com.belong.service.wechat.applet.info.api.vo.WeChatAppletLoginResultVO>
     * @throws
     * @author fengyu
     * @date 2019/12/4 17:02
     */
    @PostMapping(value = "/baseLogin", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "code码登录")
    public ResponseVO<WeChatAppletLoginResultVO> baseLogin(@RequestBody String code) {
        if (StringUtils.isNullOrEmpty(code)) {
            throw new WxAppletParameterLossException(new String[]{"code"});
        }
        WxMaJscode2SessionResult result = wxUserAuthService.wxUserLoginByCode(code);
        WxUserInfoDO wxUserInfoDO = wxUserAuthService.baseUserInfo(result);
        //生成令牌完成授权
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(wxUserInfoDO.getOpenId(), wxUserInfoDO.getOpenId())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = tokenUtil.generateToken(userDetails);
        WeChatAppletLoginResultVO weChatAppletLoginResultVO = WeChatAppletLoginResultVO.builder().access_token(token).expires_in(tokenUtil.getExpiration()).token_type(TokenUtil.TOKEN_TYPE_BEARER).userInfo(generator.convert(wxUserInfoDO, WxUserInfoVO.class)).build();
        return ResponseVO.ok(weChatAppletLoginResultVO);
    }

    /**
     * @param registryUser
     * @return
     */
    @PostMapping(value = "/completeLogin", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "敏感信息登录")
    public ResponseVO<WeChatAppletLoginResultVO> registryUser(@RequestBody WeChatRegistryUserVO registryUser) {
        if (com.belong.common.util.StringUtils.isNull(registryUser)) {
            throw new WxAppletParameterLossException();
        }
        if (StringUtils.isNullOrEmpty(registryUser.getCode())) {
            throw new WxAppletParameterLossException(new String[]{"code"});
        }
        WxMaJscode2SessionResult result = wxUserAuthService.wxUserLoginByCode(registryUser.getCode());
        WxUserInfoDO wxUserInfoDO = wxUserAuthService.userInfo(result.getSessionKey(), registryUser);
        //生成令牌
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(wxUserInfoDO.getOpenId(), wxUserInfoDO.getOpenId())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = tokenUtil.generateToken(userDetails);
        WeChatAppletLoginResultVO weChatAppletLoginResultVO = WeChatAppletLoginResultVO.builder().access_token(token).expires_in(tokenUtil.getExpiration()).token_type(TokenUtil.TOKEN_TYPE_BEARER).userInfo(generator.convert(wxUserInfoDO, WxUserInfoVO.class)).build();
        return ResponseVO.ok(weChatAppletLoginResultVO);
    }

    /**
     * @Description: 获取用户信息接口
     * @Author: fengyu
     * @CreateDate: 2019/12/5 11:01
     * @UpdateDate: 2019/12/5 11:01
     * @Version: 1.0
     */
    @ApiOperation(value = "获取用户信息")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/userInfo")
    public ResponseVO<WxUserInfoVO> info() {
        return ResponseVO.ok(getUserInfo());
    }


}
