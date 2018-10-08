package com.bkda.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.bkda.security.models.RawAccessJWT;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JWTAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {


    private AuthenticationFailureHandler failureHandler;
    private TokenExtractor tokenExtractor;
    private JWTSettings jwtSettings;

    @Autowired
    protected JWTAuthenticationProcessingFilter(AuthenticationFailureHandler failureHandler,
                                                TokenExtractor jwtHeaderExtractor,
                                                JWTSettings jwtSettings,
                                                AuthenticationManager authenticationManager) {
        super(jwtSettings.getSecuredAuthenticationEntryUrl());

        this.failureHandler = failureHandler;
//        this.jwtSettings = jwtSettings;
        this.tokenExtractor = jwtHeaderExtractor;

        List<String> pathsToSkip = Arrays.asList(jwtSettings.getUnsecuredPaths());
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, jwtSettings.getSecuredAuthenticationEntryUrl());
        this.setRequiresAuthenticationRequestMatcher(matcher);
        this.setAuthenticationManager(authenticationManager);

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse)
            throws AuthenticationException, IOException, ServletException {

        String tokenStr = httpServletRequest.getHeader(JWTSettings.JWT_TOKEN_HEADER_PARAM);
        RawAccessJWT rawAccessJWT = new RawAccessJWT(tokenExtractor.extract(tokenStr));
        return getAuthenticationManager().authenticate(new JWTAuthenticationToken(rawAccessJWT));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    @Override
    public AuthenticationFailureHandler getFailureHandler() {
        return failureHandler;
    }

    public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
    }

    public TokenExtractor getTokenExtractor() {
        return tokenExtractor;
    }

    public void setTokenExtractor(TokenExtractor tokenExtractor) {
        this.tokenExtractor = tokenExtractor;
    }

    public JWTSettings getJwtSettings() {
        return jwtSettings;
    }

    public void setJwtSettings(JWTSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }
}
