package com.scholarly.security;

import com.scholarly.dto.AuthToken;
import com.scholarly.dto.Login;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenService jwtTokenService;

    public AuthToken getToken(Login login) {
        var authToken = new UsernamePasswordAuthenticationToken(login.username(), login.password());
        Authentication auth = authenticationManager.authenticate(authToken);
        String jwtToken = jwtTokenService.generateToken(auth);
        return new AuthToken(
                ((ExtendedAuthenticationToken) auth).details().id(),
                auth.getName(),
                auth.getAuthorities().stream().toList().getFirst().getAuthority(),
                jwtToken
        );
    }

    public long getAuthenticatedUserId() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getClaim("userId");
    }
}