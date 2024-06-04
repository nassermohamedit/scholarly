package com.scholarly.security;

import com.scholarly.dto.Credentials;
import com.scholarly.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AuthenticationManagerImpl implements AuthenticationManager {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String username = auth.getName();
        Credentials creds = userRepository.findCredentialsByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String password = creds.password();
        String providedPassword = (String) auth.getCredentials();
        if (!passwordEncoder.matches(providedPassword, password)) {
            throw new BadCredentialsException("Wrong password");
        }
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(creds.role().getName())
        );
        AuthenticatedUserDetails details = new AuthenticatedUserDetails(creds.id());
        return new ExtendedAuthenticationToken(username, null, authorities, details);
    }
}