package com.scholarly.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class ExtendedAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final AuthenticatedUserDetails details;

    public ExtendedAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, AuthenticatedUserDetails details) {
        super(principal, credentials, authorities);
        this.details = details;
    }

    public AuthenticatedUserDetails details() {
        return details;
    }
}