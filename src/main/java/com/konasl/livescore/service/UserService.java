package com.konasl.livescore.service;

import com.konasl.livescore.dto.AuthRequest;
import com.konasl.livescore.dto.AuthResponse;
import com.konasl.livescore.dto.UserRequest;
import com.konasl.livescore.dto.UserResponse;

public interface UserService {

    AuthResponse login(final AuthRequest authRequest);

    UserResponse registration(final UserRequest authRequest);
}
