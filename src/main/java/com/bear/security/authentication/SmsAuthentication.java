package com.bear.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;

public class SmsAuthentication extends AbstractAuthenticationToken {

    // 自定义属性
    private String phone;
    private String code;

    private Object principal;
    private Object credentials;

    // 自定义构造方法， 用于赋值手机号和验证码
    public SmsAuthentication(String phone, String code) {
        super((Collection)null);
        this.phone = phone;
        this.code = code;
        this.setAuthenticated(false);
    }

    // 用于验证成功之后的构造
    public SmsAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }

    public String getPhone() {
        return phone;
    }

    public String getCode() {
        return code;
    }
}
