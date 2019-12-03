package com.belong.common.auth.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Description: Security User
 * @Author: fengyu
 * @CreateDate: 2019/11/28 10:24
 * @UpdateUser: fengyu
 * @UpdateDate: 2019/11/28 10:24
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public abstract class AbstractAuthUser implements UserDetails {

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
