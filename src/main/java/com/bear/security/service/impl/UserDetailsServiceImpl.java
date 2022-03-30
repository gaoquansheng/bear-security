package com.bear.security.service.impl;

import com.bear.security.entity.SecurityUser;
import com.bear.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 这里应该需要根据用户名查询数据库返回用户信息， 这里先模拟用户了
        User user = new User();
        user.setUsername("bear");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setEnabled(true);
        user.setAuthority(List.of("read"));
        return new SecurityUser(user);
    }
}
