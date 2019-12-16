package com.belong.service.wechat.applet.base.model;

import com.belong.common.auth.security.AbstractAuthUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@ApiModel(value = "AuthUser", description = "登录用户信息" )
public class AuthUser extends AbstractAuthUser {


    @ApiModelProperty(value = "ID" )
    private String id;

    @ApiModelProperty(value = "登录名" )
    private String loginName;
    @ApiModelProperty(value = "用户名" )
    private String name;
    @ApiModelProperty(value = "密码" )
    private String password;

    @ApiModelProperty(value = "邮箱" )
    private String email;

    @ApiModelProperty(value = "部门ID" )
    private String deptId;

    @ApiModelProperty(value = "手机" )
    private String mobile;

    @ApiModelProperty(value = "锁定" )
    private boolean enabled;

    @ApiModelProperty(value = "openId" )
    private String openId;

    @ApiModelProperty(value = "权限" )
    private Collection<SimpleGrantedAuthority> authorities;

    public AuthUser(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return loginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getEmail() {
        return email;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getMobile() {
        return mobile;
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
