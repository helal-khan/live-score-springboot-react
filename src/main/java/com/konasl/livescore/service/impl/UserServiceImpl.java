package com.konasl.livescore.service.impl;

import com.konasl.livescore.configuration.mapper.MapperRegistry;
import com.konasl.livescore.dto.AuthRequest;
import com.konasl.livescore.dto.AuthResponse;
import com.konasl.livescore.dto.UserRequest;
import com.konasl.livescore.dto.UserResponse;
import com.konasl.livescore.entity.User;
import com.konasl.livescore.repository.UserRepository;
import com.konasl.livescore.security.JwtProvider;
import com.konasl.livescore.security.UserPrincipal;
import com.konasl.livescore.service.UserService;
import com.konasl.livescore.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final MapperRegistry mapperRegistry;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(final AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserPrincipal userDetails = (UserPrincipal) authenticate.getPrincipal();

        return AuthResponse.builder()
                .token(jwtProvider.createToken(userDetails.getUsername(), AppConstants.DEFAULT_ROLE))
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .user(
                        UserResponse.builder()
                                .fullName(userDetails.getFullName())
                                .username(userDetails.getUsername())
                                .email(userDetails.getEmail())
                                .build()
                )
                .build();
    }

    @Override
    public UserResponse registration(UserRequest authRequest) {
        return mapperRegistry.getMapper(User.class, UserResponse.class).map(
                userRepository.save(mapperRegistry.getMapper(UserRequest.class, User.class).map(authRequest))
        );
    }
}
