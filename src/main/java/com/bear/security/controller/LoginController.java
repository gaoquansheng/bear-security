package com.bear.security.controller;

import com.bear.security.service.LoginService;
import com.bear.security.vo.LoginVO;
import com.bear.security.vo.SmsLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginVO loginVO) {

        return loginService.login(loginVO);
    }

    @PostMapping("/sms/login")
    public String smsLogin(@RequestBody SmsLoginVO smsLoginVO) {

        return loginService.smsLogin(smsLoginVO);
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('read')")
    public void test() {
        System.out.println("测试权限接口");
    }
}