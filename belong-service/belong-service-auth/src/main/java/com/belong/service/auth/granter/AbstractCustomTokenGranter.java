package com.belong.service.auth.granter;

import com.belong.common.auth.entity.BelongAuthUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2020/1/3 17:05
*/
public abstract class AbstractCustomTokenGranter extends AbstractTokenGranter {

    protected AbstractCustomTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory, String grantType) {
        super(tokenServices, clientDetailsService, requestFactory, grantType);
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap(tokenRequest.getRequestParameters());
        BelongAuthUser belongAuthUser = getCustomUser(parameters);
        if (belongAuthUser == null) {
            throw new InvalidGrantException("无法获取用户信息!");
        }
        OAuth2Authentication authentication = super.getOAuth2Authentication(client, tokenRequest);
        authentication.setDetails(belongAuthUser);
        authentication.setAuthenticated(true);
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    protected abstract BelongAuthUser getCustomUser(Map<String, String> parameters);

}
