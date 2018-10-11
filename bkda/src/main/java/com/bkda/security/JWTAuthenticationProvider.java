package com.bkda.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bkda.security.JWTAuthenticationToken;
import com.bkda.security.JWTSettings;
import com.bkda.security.models.AccountPermission;
import com.bkda.security.models.MPPrincipal;
import com.bkda.security.models.RawAccessJWT;
import com.bkda.security.models.Scope;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthenticationProvider implements AuthenticationProvider {
    private JWTSettings jwtSettings;
    private static Logger log = LoggerFactory.getLogger(JWTAuthenticationProvider.class);

    @Autowired
    public JWTAuthenticationProvider(JWTSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RawAccessJWT rawAccessToken = (RawAccessJWT) authentication.getCredentials();
        return convertRawTokenToAuthenticationToken(rawAccessToken);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JWTAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private JWTAuthenticationToken convertRawTokenToAuthenticationToken(RawAccessJWT rawAccessToken) {
        Jws<Claims> claims = null;
        try {
            claims = rawAccessToken.parseClaims(jwtSettings.getTokenSigningKey());
            Date expDate = claims.getBody().getExpiration();
            if (expDate != null && log.isDebugEnabled()) {
                log.debug("Token expires {}", expDate.toString());
            }

            String subject = claims.getBody().getSubject();
            log.info("claims '{}'", claims);
            ArrayList<String> permissions;
            permissions = claims.getBody().get("scope", ArrayList.class);
            ObjectMapper mapper = new ObjectMapper();
            String jsonScope = mapper.writeValueAsString(permissions);
            log.debug("jsonScope string parsed from claims in token: '{}'", jsonScope);
            List<AccountPermission> permissionsList = permissions.stream().map(AccountPermission::new).collect(Collectors.toList());
            Scope scope = new Scope();
            scope.setPermissions(permissionsList);
            // This will ignore the check for application validation now as that's moving to GW
            MPPrincipal context = MPPrincipal.instantiate(subject, scope.getPermissions());
            return new JWTAuthenticationToken(context, context.getAuthorities());
        } catch (AuthenticationException ae) {
            log.error("Problem processing JWT Token.  Error: '%s'", ae);
            throw ae;
        } catch (Exception exc) {
            log.error("Problem processing JWT Token.  Error: '%s'", exc);
            throw new IllegalArgumentException("Problem processing JWT Token", exc);
        }
    }

    public JWTSettings getJwtSettings() {
        return jwtSettings;
    }

    public void setJwtSettings(JWTSettings jwtSettings) {
        this.jwtSettings = jwtSettings;
    }
}
