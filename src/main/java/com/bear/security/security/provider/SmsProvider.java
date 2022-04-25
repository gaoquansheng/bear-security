package com.bear.security.security.provider;

import com.bear.security.security.authentication.SmsAuthentication;
import com.bear.security.security.exception.CustomerSecurityException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class SmsProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        SmsAuthentication smsAuthentication = (SmsAuthentication) authentication;
        String phone = smsAuthentication.getPhone();
        String code = smsAuthentication.getCode();
        String testPhone = "15516392395";
        String testCode = "123456";
        if (testPhone.equals(phone) && testCode.equals(code)) {
            return new SmsAuthentication("bear", "211318",null);
        }else{
            throw new CustomerSecurityException("手机号或短信验证码错误");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthentication.class.isAssignableFrom(authentication);
    }
}
