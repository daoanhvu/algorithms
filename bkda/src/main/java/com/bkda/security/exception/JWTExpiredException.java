package com.bkda.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JWTExpiredException extends AuthenticationException {

    public JWTExpiredException(String msg) {
        super(msg);
    }

    public JWTExpiredException(String msg, Throwable t) {
        super(msg, t);
    }
}
