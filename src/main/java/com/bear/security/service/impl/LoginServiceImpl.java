package com.bear.security.service.impl;

import com.bear.security.security.authentication.*;
import com.bear.security.service.LoginService;
import com.bear.security.vo.LoginVO;
import com.bear.security.vo.SmsLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginVO loginVO) {

        Authentication token = new UsernamePasswordAuthenticationToken(loginVO.getUsername(), loginVO.getPassword());

        Authentication authenticate = authenticationManager.authenticate(token);

        return authenticate.getName();
    }

    @Override
    public String smsLogin(SmsLoginVO smsLoginVO) {

        SmsAuthentication token = new SmsAuthentication(smsLoginVO.getPhone(), smsLoginVO.getCode());

        Authentication authenticate = authenticationManager.authenticate(token);
        return authenticate.getName();
    }
}
