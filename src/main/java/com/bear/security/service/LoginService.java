package com.bear.security.service;

import com.bear.security.vo.LoginVO;
import com.bear.security.vo.SmsLoginVO;

public interface LoginService {

    String login(LoginVO loginVO);

    String smsLogin(SmsLoginVO smsLoginVO);
}
