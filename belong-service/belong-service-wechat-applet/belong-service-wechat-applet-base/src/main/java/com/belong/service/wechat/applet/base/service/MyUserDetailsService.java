package com.belong.service.wechat.applet.base.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MyUserDetailsService extends UserDetailsService {

    UserDetails loadUserByCode(String code);

    UserDetails loadUserByMobile(String mobile);
}
