package com.bear.security.controller;

import com.bear.security.security.authentication.SmsAuthentication;
import com.bear.security.service.LoginService;
import com.bear.security.vo.LoginVO;
import com.bear.security.vo.SmsLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    // 登录方式一 ： 用户名密码登录
    @PostMapping("/login")
    public String login(@RequestBody LoginVO loginVO) {

        Authentication token = new UsernamePasswordAuthenticationToken(loginVO.getUsername(), loginVO.getPassword());
        return loginService.login(token);
    }

    // 登录方式二 ：手机号和验证码登录
    @PostMapping("/sms/login")
    public String smsLogin(@RequestBody SmsLoginVO smsLoginVO) {

        Authentication token = new SmsAuthentication(smsLoginVO.getPhone(), smsLoginVO.getCode());
        return loginService.login(token);
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('read')")
    public void test() {
        System.out.println("测试权限接口");
    }
}
