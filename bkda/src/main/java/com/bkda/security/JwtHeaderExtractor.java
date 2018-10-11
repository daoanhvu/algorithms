package com.bkda.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

@Component
public class JwtHeaderExtractor implements TokenExtractor {

    static final String BEARER = "Bearer";

    @Override
    public String extract(String payload) {
        if( payload == null || payload.length() < BEARER.length() )
            throw new BadCredentialsException("Invalid JWT");
        return payload.substring(BEARER.length());
    }
}
