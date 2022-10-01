package com.konasl.livescore.mapper;

import com.konasl.livescore.configuration.mapper.Mapper;
import com.konasl.livescore.configuration.mapper.MapperRegistry;
import com.konasl.livescore.dto.UserRequest;
import com.konasl.livescore.dto.UserResponse;
import com.konasl.livescore.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UserMappers {

    private final PasswordEncoder passwordEncoder;
    private final MapperRegistry mapperRegistry;

    @PostConstruct
    private void registerMappers() {
        mapperRegistry.addMapper(UserRequest.class, User.class, userRequestToUserMapper());
        mapperRegistry.addMapper(User.class, UserResponse.class, userToUserResponseMapper());
    }

    private Mapper<UserRequest, User> userRequestToUserMapper() {
        return user -> User.builder()
                .fullName(user.getFullName())
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .build();
    }

    private Mapper<User, UserResponse> userToUserResponseMapper() {
        return user -> UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
