package com.bkda.service;

import java.security.Key;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.NotNull;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bkda.dto.CredentialsDTO;
import com.bkda.exception.BKDAServiceException;
import com.bkda.model.User;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthenticationService {
	
//	@Value("${jwt.tokenSigninKey}")
	private String tokenSigninKey = "sdfasdf";
	
//	@Value("${jwt.Issuer}")
	private String tokenIssuer = "bkda.com";

	public CredentialsDTO createCredentials(@NotNull User user, 
			@NotNull String grantType) throws BKDAServiceException {
		
		SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
		CredentialsDTO credentials = new CredentialsDTO();
		Date now = Date.from(Instant.now(Clock.systemUTC()));
        //TODO: set duration of the token to 1 hour
        Date dueDate = DateUtils.addHours(now, 1);
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(tokenSigninKey);
        Key signKey = new SecretKeySpec(keyBytes, algorithm.getJcaName());
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("iss", tokenIssuer);
        claims.put("iat", now);
        claims.put("username", user.getUsername());
        claims.put("grantType", grantType);
        claims.put("scope", user.getScopes());
        claims.put("exp", dueDate);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                .signWith(algorithm, signKey);
        
		credentials.setAccessToken(jwtBuilder.compact());
	
		return credentials;
	}
	
}
