package com.bear.security.security.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * 短信验证码登录的身份验证信息
 */
public class SmsAuthentication extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 560L;
    private Object principal;
    private Object credentials;
    private String phone;
    private String code;

    // 认证之前的构造函数
    public SmsAuthentication(String phone, String code) {
        super((Collection)null);
        this.phone = phone;
        this.code = code;
        this.setAuthenticated(false);
    }

    // 认证之后的构造函数
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
