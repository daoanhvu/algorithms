package com.bkda.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JWTSettings {

    public final static String JWT_TOKEN_HEADER_PARAM = "Authorization";
    private int tokenExpirationTime;

    private String tokenIssuer;
    private String tokenSigningKey;

    private int refreshTokenExpirationTime;

    private String securedAuthenticationEntryUrl;

    private String[] unsecuredPaths;
    private String[] applications;

    public int getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(int tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public String getTokenIssuer() {
        return tokenIssuer;
    }

    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }

    public String getTokenSigningKey() {
        return tokenSigningKey;
    }

    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }

    public int getRefreshTokenExpirationTime() {
        return refreshTokenExpirationTime;
    }

    public void setRefreshTokenExpirationTime(int refreshTokenExpirationTime) {
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
    }

    public String getSecuredAuthenticationEntryUrl() {
        return securedAuthenticationEntryUrl;
    }

    public void setSecuredAuthenticationEntryUrl(String securedAuthenticationEntryUrl) {
        this.securedAuthenticationEntryUrl = securedAuthenticationEntryUrl;
    }

    public String[] getUnsecuredPaths() {
        return unsecuredPaths;
    }

    public void setUnsecuredPaths(String[] unsecuredPaths) {
        this.unsecuredPaths = unsecuredPaths;
    }

    public String[] getApplications() {
        return applications;
    }

    public void setApplications(String[] applications) {
        this.applications = applications;
    }
}
