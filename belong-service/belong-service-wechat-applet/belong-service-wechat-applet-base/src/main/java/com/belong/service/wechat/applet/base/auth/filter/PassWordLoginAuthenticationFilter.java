package com.belong.service.wechat.applet.base.auth.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PassWordLoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    private static final String SPRING_SECURITY_RESTFUL_USERNAME_KEY = "username" ;
    private static final String SPRING_SECURITY_RESTFUL_PASSWORD_KEY = "password" ;

    private static final String SPRING_SECURITY_RESTFUL_LOGIN_URL = "/login" ;
    private boolean postOnly = true;

    public PassWordLoginAuthenticationFilter() {
        super(new AntPathRequestMatcher(SPRING_SECURITY_RESTFUL_LOGIN_URL, "POST" ));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST" )) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String principal = obtainParameter(request, SPRING_SECURITY_RESTFUL_USERNAME_KEY);
        String credentials = obtainParameter(request, SPRING_SECURITY_RESTFUL_PASSWORD_KEY);
        principal = principal.trim();
        AbstractAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(principal, credentials);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request,
                            AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainParameter(HttpServletRequest request, String parameter) {
        String result = request.getParameter(parameter);
        return result == null ? "" : result;
    }
}
