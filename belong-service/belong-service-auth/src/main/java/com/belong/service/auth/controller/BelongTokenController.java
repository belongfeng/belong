package com.belong.service.auth.controller;

import cn.hutool.core.util.StrUtil;
import com.belong.common.auth.util.SecurityUtils;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description: 删除token端点
 * @Author: fengyu
 * @CreateDate: 2020/1/7 13:44
 */
@RestController
@AllArgsConstructor
@RequestMapping("/token")
public class BelongTokenController {
    @Autowired
    private final ClientDetailsService clientDetailsService;
    private final TokenStore tokenStore;

    /**
     * 认证页面
     *
     * @param modelAndView
     * @return ModelAndView
     */
    @GetMapping("/login")
    public ModelAndView require(ModelAndView modelAndView) {
        modelAndView.setViewName("ftl/login");
        return modelAndView;
    }

    /**
     * 确认授权页面
     *
     * @param request+
     * @param session
     * @param modelAndView
     * @return
     */
    @GetMapping("/confirm_access")
    public ModelAndView confirm(HttpServletRequest request, HttpSession session, ModelAndView modelAndView) {
        Map<String, Object> scopeList = (Map<String, Object>) request.getAttribute("scopes");
        modelAndView.addObject("scopeList", scopeList.keySet());

        Object auth = session.getAttribute("authorizationRequest");
        if (auth != null) {
            AuthorizationRequest authorizationRequest = (AuthorizationRequest) auth;
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
            modelAndView.addObject("app", clientDetails.getAdditionalInformation());
            modelAndView.addObject("user", SecurityUtils.getUser());
        }

        modelAndView.setViewName("ftl/confirm");
        return modelAndView;
    }

    /**
     * 退出token
     *
     * @param authHeader Authorization
     */
    @DeleteMapping("/logout")
    public ResponseVO logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {
        if (StrUtil.isBlank(authHeader)) {
            return ResponseVO.ok("退出失败，token 为空");
        }
        String tokenValue = authHeader.replace(OAuth2AccessToken.BEARER_TYPE, StrUtil.EMPTY).trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        if (accessToken == null || StrUtil.isBlank(accessToken.getValue())) {
            return ResponseVO.ok("退出失败，token 无效");
        }
        OAuth2Authentication auth2Authentication = tokenStore.readAuthentication(accessToken);
        // 清空access token
        tokenStore.removeAccessToken(accessToken);
        // 清空 refresh token
        OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
        if (StringUtils.isNotNull(refreshToken)) {
            tokenStore.removeRefreshToken(refreshToken);
        }
        return ResponseVO.ok();
    }

    /**
     * 令牌管理调用
     *
     * @param token token
     * @return
     */
    @DeleteMapping("/{token}")
    public ResponseVO delToken(@PathVariable("token") String token) {
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return ResponseVO.ok();
    }

}
