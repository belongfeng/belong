package com.belong.service.wechat.applet.base.service;//package com.belong.service.wechat.base.security;

import com.belong.service.wechat.applet.base.model.AuthUser;
import com.belong.service.wechat.applet.base.model.AuthUserFactory;
import com.belong.service.wechat.applet.info.api.feign.RemoteWxUserAuthFService;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Description: Security User Detail Service
 * @Author: fengyu
 * @CreateDate: 2019/12/4 11:44
 * @UpdateDate: 2019/12/4 11:44
 * @Version: 1.0
 */
@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {
    /**
     * 密码加密
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RemoteWxUserAuthFService remoteWxUserAuthFService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String openid) {
        String pwd = passwordEncoder.encode("123456");
        AuthUser authUser = new AuthUser("asdas");
        authUser.setLoginName("admin");
        authUser.setPassword(pwd);
        return authUser;
    }

    @Override
    public UserDetails loadUserByCode(String code) {
        WxUserInfoVO wxUserInfoVO = remoteWxUserAuthFService.baseLogin(code).getData();
        return AuthUserFactory.create(wxUserInfoVO);
    }

    @Override
    public UserDetails loadUserByMobile(String mobile) {
        String pwd = passwordEncoder.encode("123456" );
        AuthUser authUser = new AuthUser("asdas" );
        authUser.setLoginName("admin" );
        authUser.setPassword(pwd);
        return authUser;
    }
}
