package com.belong.service.auth.service;

import com.belong.common.auth.entity.BelongAuthUser;
import com.belong.service.wechat.applet.info.api.feign.RemoteWxUserAuthFService;
import com.belong.service.wechat.applet.info.api.vo.WxUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Service;

/**
 * @Classname UserDetailService
 * @Description TODO
 * @Date 2020/1/2 15:11
 * @Created by FengYu
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private RemoteWxUserAuthFService remoteWxUserAuthFService;

    @Override
    public BelongAuthUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public BelongAuthUser loadUserByWechatAppletCode(String code) throws UsernameNotFoundException {
        //WxUserInfoVO wxUserInfoVO = remoteWxUserAuthFService.baseLogin(code).getData();
        WxUserInfoVO wxUserInfoVO=new WxUserInfoVO();
        wxUserInfoVO.setEnabled(true);
        wxUserInfoVO.setAvatarUrl("img");
        wxUserInfoVO.setMobile("setMobile");
        wxUserInfoVO.setId("123456789");
        wxUserInfoVO.setNickName("name");
        if (wxUserInfoVO == null) {
            throw new InvalidGrantException("请再次检查code是否正确，尝试刷新！");
        }
        BelongAuthUser belongAuthUser = BelongAuthUser.builder().accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true).accountNonLocked(true)
                .enabled(wxUserInfoVO.getEnabled()).avatar(wxUserInfoVO.getAvatarUrl()).userId(wxUserInfoVO.getId()).mobile(wxUserInfoVO.getMobile()).username(wxUserInfoVO.getNickName()).build();
        return belongAuthUser;
    }

}
