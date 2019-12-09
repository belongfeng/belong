package com.belong.service.wechat.applet.base;//package com.belong.service.wechat.base.security;

import com.alibaba.fastjson.JSON;
import com.belong.common.core.base.ResponseVO;
import com.belong.common.util.StringUtils;
import com.belong.service.wechat.applet.base.model.AuthUserFactory;
import com.belong.service.wechat.applet.info.api.domain.WxUserInfoDO;
import com.belong.service.wechat.applet.info.api.feign.RemoteWxUserInfoDOFService;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * 密码加密
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    /**
     * 用户服务
     */
    @Autowired
    private RemoteWxUserInfoDOFService remoteWxUserInfoDOFService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String openid) {
        ResponseVO<WxUserInfoVO> responseVO = remoteWxUserInfoDOFService.getWxUserInfoByOpenId(openid);
        if (StringUtils.isNull(responseVO) || StringUtils.isNull(responseVO.getData())) {
            throw new UsernameNotFoundException(String.format("No user found with openid '%s'.", openid));
        } else {
            WxUserInfoVO user = responseVO.getData();
            user.setTokenPwd(passwordEncoder.encode(openid));
            redisTemplate.opsForValue().set(WxUserInfoDO.REDIS_KEY + openid, JSON.toJSONString(user));
            return AuthUserFactory.create(user);
        }
    }
}
