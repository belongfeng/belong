package com.belong.service.auth.controller;

import com.belong.common.core.base.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: yugu
 * @CreateDate: 2019/9/6
 * @Description:
 */
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @GetMapping("/token")
    public ResponseVO getAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.getAccessToken(principal, parameters).getBody());
    }

    @PostMapping("/token")
    public ResponseVO postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

    /**
     * 方法实现说明:定制申请返回实体
     *
     * @param accessToken
     * @return com.belong.common.core.base.ResponseVO
     * @throws
     * @author belongfeng
     */
    private ResponseVO custom(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        //Map<String, Object> data = new LinkedHashMap(token.getAdditionalInformation());
        Map<String, Object> data = new LinkedHashMap(5);
        data.put("accessToken", token.getValue());
        data.put("expires_in", token.getExpiresIn());
        data.put("tokenType", token.getTokenType());
        data.put("scope", token.getScope());
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken().getValue());
        }
        return ResponseVO.ok(data);
    }

}
