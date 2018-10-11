package com.bkda.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bkda.dto.BaseResponse;
import com.bkda.security.exception.JWTExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Component
public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private static final String INVALID_JWT = "Invalid JWT token";
    private static final String EXPIRED_JWT = "Token has expired";
    private static final String AUTHEN_FAILED = "Authentication failed";
    private final ObjectMapper objectMapper;

    @Autowired
    public JWTAuthenticationFailureHandler(ObjectMapper mapper) {
        this.objectMapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        if( e instanceof BadCredentialsException) {
            objectMapper.writeValue(response.getWriter(), new BaseResponse(0, INVALID_JWT));
            return;
        }

        if( e instanceof JWTExpiredException) {
            objectMapper.writeValue(response.getWriter(), new BaseResponse(0, EXPIRED_JWT));
            return;
        }

        objectMapper.writeValue(response.getWriter(), new BaseResponse(0, AUTHEN_FAILED));
    }
}
