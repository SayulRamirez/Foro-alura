package com.challeng.foro.security;

import com.challeng.foro.domain.AuthResponse;
import com.challeng.foro.domain.LoginRequest;
import com.challeng.foro.domain.RegisterRequest;
import com.challeng.foro.entities.UserEntity;
import com.challeng.foro.enums.Role;
import com.challeng.foro.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest request) {

        UserEntity user = new UserEntity(
                null,
                request.username(),
                passwordEncoder.encode(request.password()),
                Role.USER
        );

        userRepository.save(user);

        return new AuthResponse(jwtService.getToken(user));
    }

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        UserDetails user = userRepository.findByUsername(request.username()).orElseThrow();

        String token = jwtService.getToken(user);

        return new AuthResponse(token);
    }
}
