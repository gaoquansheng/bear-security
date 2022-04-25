package com.bear.security.security.exception;

import org.springframework.security.authentication.InternalAuthenticationServiceException;

public class CustomerSecurityException extends InternalAuthenticationServiceException {
    public CustomerSecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerSecurityException(String message) {
        super(message);
    }
}
