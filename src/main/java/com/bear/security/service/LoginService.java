package com.bear.security.service;

import org.springframework.security.core.Authentication;

public interface LoginService {

    String login(Authentication authentication);

}
