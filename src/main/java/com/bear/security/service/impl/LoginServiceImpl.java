package com.bear.security.service.impl;

import com.bear.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(Authentication authentication) {

        try {
            Authentication authenticate = authenticationManager.authenticate(authentication);
            return this.successHandler(authenticate);
        }catch (Exception e) {
            return this.failureHandler(e);
        }

    }

    private String successHandler(Authentication authentication) {
        return "登录成功";
    }

    private String failureHandler(Exception exception) {

        String message = null;
        if (exception instanceof LockedException) {
            message = "账户被锁定，请联系管理员!";
        } else if (exception instanceof CredentialsExpiredException) {
            message = "密码过期，请联系管理员!";

        } else if (exception instanceof AccountExpiredException) {
            message = "账户过期，请联系管理员!";

        } else if (exception instanceof DisabledException) {
            message = "账户被禁用，请联系管理员!";

        } else if (exception instanceof BadCredentialsException) {
            message = "用户名或者密码输入错误，请联系管理员!";
        }else {
            message = "账户异常";
        }
        return message;
    }
}
