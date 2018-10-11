package com.bkda.security.models;

import com.bkda.security.exception.JWTExpiredException;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;

public class RawAccessJWT {

    private static final long serialVersionUID = 13293862194357L;

    private static Logger logger = LoggerFactory.getLogger(RawAccessJWT.class);

    private String token;

    public RawAccessJWT(String token) {
        this.token = token;
    }

    /**
     * Parses and validates JWT using a given signature.
     *
     * @throws BadCredentialsException on bad JWT
     * @throws JWTExpiredException on expiration
     *
     */
    public Jws<Claims> parseClaims(String signingKey) {
        try {
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token);
            logger.debug("Parsed the following JWS claims from the token->{}", jwsClaims);
            return jwsClaims;
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
            logger.error("Invalid JWT Token", ex);
            throw new BadCredentialsException("Invalid JWT token: ", ex);
        } catch (ExpiredJwtException expiredEx) {
            logger.info("JWT Token is expired", expiredEx);
            throw new JWTExpiredException("JWT Token expired", expiredEx);
        }
    }

    public String getToken() {
        return token;
    }
}
