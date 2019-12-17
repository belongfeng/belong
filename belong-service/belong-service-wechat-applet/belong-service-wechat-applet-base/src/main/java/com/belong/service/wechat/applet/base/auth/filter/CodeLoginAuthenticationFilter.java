package com.belong.service.wechat.applet.base.auth.filter;

import com.belong.common.auth.security.LoginConstants;
import com.belong.common.util.HttpServletRequestReader;
import com.belong.common.util.json.JSONUtils;
import com.belong.service.wechat.applet.base.auth.token.CodeAuthenticationToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
* @Description:    微信小程序登录拦截
* @Author:         fengyu
* @CreateDate:     2019/12/17 15:31
*/
public class CodeLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private boolean postOnly = true;

    public CodeLoginAuthenticationFilter() {
        super(new AntPathRequestMatcher(LoginConstants.MINI_APP_LOGIN, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        AbstractAuthenticationToken authRequest;
        String principal = obtainParameter(request, LoginConstants.SPRING_SECURITY_RESTFUL_CODE_KEY);
        String credentials = "" ;
        principal = principal.trim();
        authRequest = new CodeAuthenticationToken(principal, credentials);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request,
                            AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainParameter(HttpServletRequest request, String parameter) {
        String result = HttpServletRequestReader.readAsChars(request);
        Map map = JSONUtils.jsonToMap(result);
        return result == null ? "" : map.get(parameter).toString();
    }
}
