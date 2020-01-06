package com.belong.service.auth.granter;

import com.belong.common.auth.entity.BelongAuthUser;
import com.belong.service.auth.service.UserDetailService;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.Map;

/**
* @Description:    用于微信小程序code码授权
* @Author:         fengyu
* @CreateDate:     2020/1/3 17:12
*/
public class WechatAppletCodeCustomTokenGranter extends AbstractCustomTokenGranter {

    protected UserDetailService userDetailsService;

    public WechatAppletCodeCustomTokenGranter(UserDetailService userDetailsService, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, "wacode");
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected BelongAuthUser getCustomUser(Map<String, String> parameters) {
        String code = parameters.get("code");
        return userDetailsService.loadUserByWechatAppletCode(code);
    }

}
