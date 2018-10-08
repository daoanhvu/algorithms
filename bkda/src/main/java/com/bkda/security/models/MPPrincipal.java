package com.bkda.security.models;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class MPPrincipal {

    private final String username;
    private final List<GrantedAuthority> authorities;

    public MPPrincipal(String username, List<GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public static MPPrincipal instantiate(String username, List<AccountPermission> permissions) {
        if(StringUtils.isEmpty(username))
            throw new BadCredentialsException("Username cannot be blank!");

        List<GrantedAuthority> auths = permissions.stream()
                .map(AccountPermission::toString)
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new MPPrincipal(username, auths);
    }

    public String getUsername() {
        return username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
