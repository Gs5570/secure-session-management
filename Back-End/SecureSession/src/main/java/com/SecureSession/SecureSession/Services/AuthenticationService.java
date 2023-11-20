package com.SecureSession.SecureSession.Services;

import com.SecureSession.SecureSession.Repo.UserRepo;
import com.SecureSession.SecureSession.Model.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    public AuthenticationResponse register(RegisterRequest request) {
        var user= User.builder().username(request.getUserName()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
        userRepo.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
    public AuthenticationResponse registerAdmin(RegisterRequest request) {
        var user= User.builder().username(request.getUserName()).password(passwordEncoder.encode(request.getPassword())).role(Role.ADMIN).build();
        userRepo.save(user);
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        log.debug("Beginning authentication for user: {}", request.getUsername());
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        log.debug("Authentication success for user: {}", authentication.getName());
        var user=userRepo.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
