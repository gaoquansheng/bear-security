package com.bear.security.provider;

import com.bear.security.authentication.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SmsProvider implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsAuthentication smsAuthentication = (SmsAuthentication) authentication;
        String phone = smsAuthentication.getPhone();
        String code = smsAuthentication.getCode();
        String tmpPhone = "15516392395";
        String tmpCode = "211318";
        if (tmpPhone.equals(phone) && tmpCode.equals(code)) {
            // 首先自定义判断逻辑， 判断手机号和密码是否正确，
            // 然后根据手机号查询用户信息， 将用户信息重新封装进行返回
            SimpleGrantedAuthority read = new SimpleGrantedAuthority("read");
            return new SmsAuthentication("bear", "211318", List.of(read));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthentication.class.isAssignableFrom(authentication);
    }
}
