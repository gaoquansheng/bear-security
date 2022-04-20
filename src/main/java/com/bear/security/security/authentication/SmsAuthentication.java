package com.bear.security.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 短信验证码登录的身份验证信息
 */
public class SmsAuthentication extends UsernamePasswordAuthenticationToken {

    private String code;
    public SmsAuthentication(Object principal, Object credentials, String code) {
        super(principal, credentials);
        this.code = code;
    }

    public SmsAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
