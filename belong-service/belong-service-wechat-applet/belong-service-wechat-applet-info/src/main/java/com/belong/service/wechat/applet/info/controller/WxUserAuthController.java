package com.belong.service.wechat.applet.info.controller;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.belong.common.core.base.BaseController;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.exception.wxapplet.parameter.WxAppletParameterLossException;
import com.belong.service.wechat.applet.base.utils.TokenUtil;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;
import com.belong.service.wechat.applet.info.api.vo.WeChatAppletLoginResultVO;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import com.belong.service.wechat.applet.info.service.IWxUserAuthService;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/12/4 14:27
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
@Api(tags = "微信用户登录")
@RestController
@AllArgsConstructor
@RequestMapping("/v1/api/wxUserAuth")
@Slf4j
public class WxUserAuthController extends BaseController {
    @Autowired
    private final IWxUserAuthService wxUserAuthService;
    /**
     * 权限管理
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * Token工具类
     */
    @Autowired
    private TokenUtil jwtTokenUtil;


    @PostMapping(value = "/baseLogin", produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "基础登录")
    public ResponseVO<WeChatAppletLoginResultVO> baseLogin(@RequestBody String code) {
        if (StringUtils.isNullOrEmpty(code)) {
            throw new WxAppletParameterLossException();
        }
        WxMaJscode2SessionResult result = wxUserAuthService.wxUserLoginByCode(code);
        WxUserInfoDO wxUserInfoDO = wxUserAuthService.baseUserInfo(result);
        //完成授权
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(wxUserInfoDO.getOpenId(), wxUserInfoDO.getOpenId())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails);
        WeChatAppletLoginResultVO weChatAppletLoginResultVO = WeChatAppletLoginResultVO.builder().access_token(token).userInfo(generator.convert(wxUserInfoDO, WxUserInfoVO.class)).build();
        return ResponseVO.ok(weChatAppletLoginResultVO);
    }


}
