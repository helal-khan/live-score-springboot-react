package com.konasl.livescore.dto;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthResponse {
    private String token;
    private Instant expiresAt;
    private UserResponse user;
}