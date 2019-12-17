package com.belong.service.wechat.applet.base.auth.handler;

import com.belong.common.core.base.ResponseVO;
import com.belong.service.wechat.applet.base.model.AuthUser;
import com.belong.service.wechat.applet.base.utils.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 当用户登录成功之后做的处理
 * @Author: fengyu
 * @CreateDate: 2019/12/16 15:20
 */
@Component
@Slf4j
public class MiniAppAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        final AuthUser userDetails = (AuthUser) authentication.getPrincipal();
        log.info("小程序用户登录成功！用户为----->{}", userDetails.getOpenId());
        final String token = tokenUtil.generateToken(userDetails);
        Map map = new HashMap(3);
        map.put("access_token", token);
        map.put("expires_in", tokenUtil.getExpiration());
        map.put("token_type", TokenUtil.TOKEN_TYPE_BEARER);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(ResponseVO.ok(map)));
    }
}
