//package com.belong.service.wechat.applet.base;//package com.belong.service.wechat.base.security;
//
//import cn.lioncity.platform.admin.base.security.model.AuthUserFactory;
//import cn.lioncity.platform.common.constant.DubboVersion;
//import cn.lioncity.platform.common.constant.ServiceNameConstants;
//import cn.lioncity.platform.system.api.sys.domain.SysUserDO;
//import cn.lioncity.platform.system.api.sys.service.ISystemService;
//import com.alibaba.dubbo.config.annotation.Reference;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
///**
// * Security User Detail Service
// *
// * @author lioncity
// */
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    /**
//     * 密码加密
//     */
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    /**
//     * 用户服务
//     */
//    @Autowired(required = false)
//    private IWxUserInfoService wxUserInfoService;
//    @Autowired
//    RedisRepository redisRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String openid) {
//        WxUserInfoDO user = wxUserInfoService.selectByOpenId(openid);
//
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("No user found with openid '%s'.", openid));
//        } else {
//            user.setTokenPwd(passwordEncoder.encode(openid));
//            redisRepository.set(WxUserInfoDO.REDIS_KEY + openid, JSONUtils.beanToJson(user));
//            return AuthUserFactory.create(user);
//        }
//    }
//}
