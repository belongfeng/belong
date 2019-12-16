package com.belong.service.wechat.applet.base.auth.handler;

import com.belong.common.core.constant.EnumErrorCode;
import com.belong.common.exception.wxapplet.login.MiniAppLoginException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Component
public class FormAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Called when an authentication attempt fails.
     *
     * @param request   the request during which the authentication attempt occurred.
     * @param response  the response.
     * @param exception the exception which was thrown to reject the authentication
     */
    @Override
    @SneakyThrows
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        log.error("登录失败:{}", exception);
        Integer code = -1;
        Map map = new HashMap();
        if (exception instanceof DisabledException) {
            code = EnumErrorCode.DISABLED_USER.getCode();
        } else if (exception instanceof LockedException) {
            code = EnumErrorCode.DISABLED_USER.getCode();
        } else if (exception instanceof AccountExpiredException) {
            code = EnumErrorCode.DISABLED_USER.getCode();
        } else if (exception instanceof BadCredentialsException) {
            code = EnumErrorCode.INVALID_GRANT.getCode();
        } else if (exception instanceof MiniAppLoginException) {
            code = EnumErrorCode.INVALID_GRANT.getCode();
        } else if (exception instanceof InternalAuthenticationServiceException) {
            code = EnumErrorCode.INVALID_GRANT.getCode();
        }
        map.put("code", code.toString());
        map.put("msg", exception.getMessage());
        map.put("data", null);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(map));
    }
}