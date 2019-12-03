package com.belong.service.wechat.applet.base.model;

import com.belong.common.auth.security.AbstractAuthUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class AuthUser extends AbstractAuthUser {

    /**
     * 用户默认角色
     */
    private static final String TRIP_USER_ROLE = "ROLE_USER";
    /**
     * id
     */
    private String id;
    /**
     * openid
     */
    private String openid;
    /**
     * unionid
     */
    private String unionid;
    /**
     * 加密后的openid
     */
    private String encoderOpenid;
    /**
     * 冻结
     */
    private boolean enabled;

    public AuthUser(
            String id,
            String openid,
            String unionid,
            String encoderOpenid,
            boolean enabled
    ) {
        this.id = id;
        this.openid = openid;
        this.unionid = unionid;
        this.encoderOpenid = encoderOpenid;
        this.enabled = enabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getEncoderOpenid() {
        return encoderOpenid;
    }

    @Override
    public String getPassword() {
        return encoderOpenid;
    }

    public void setEncoderOpenid(String encoderOpenid) {
        this.encoderOpenid = encoderOpenid;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String getUsername() {
        return openid;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(TRIP_USER_ROLE));
        return authorities;
    }

}
