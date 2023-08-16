package com.test.socialmedia.api.security;

import com.test.socialmedia.model.request.AuthenticationRequest;
import com.test.socialmedia.model.request.RegisterRequest;
import com.test.socialmedia.model.response.AuthenticationResponse;
import com.test.socialmedia.persist.UserRepository;
import com.test.socialmedia.persist.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var userEntity = UserEntity.builder()
                .login(registerRequest.getLogin())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        userRepository.save(userEntity);
        var jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getLogin(),
                        authenticationRequest.getPassword()
                )
        );
        var userEntity = userRepository.findByLogin(authenticationRequest.getLogin())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(userEntity);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
