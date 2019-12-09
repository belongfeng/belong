package com.belong.service.wechat.applet.base.utils;//package com.belong.service.wechat.base.security.utils;

import com.alibaba.fastjson.JSONObject;
import com.belong.common.auth.security.AbstractTokenUtil;
import com.belong.service.wechat.applet.base.model.AuthUser;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @Description: The type Token util.
 * @Author: fengyu
 * @CreateDate: 2019/12/4 11:25
 * @UpdateDate: 2019/12/4 11:25
 * @Version: 1.0
 */
@Component
@ConfigurationProperties("security.jwt")
public class TokenUtil extends AbstractTokenUtil {

    @Override
    public UserDetails getUserDetails(String token) {
        String userDetailsString = getUserDetailsString(token);
        if (userDetailsString != null) {
            return JSONObject.parseObject(userDetailsString, AuthUser.class);
        }
        return null;
    }

}