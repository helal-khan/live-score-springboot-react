package com.konasl.livescore.controller;


import com.konasl.livescore.dto.AuthRequest;
import com.konasl.livescore.dto.AuthResponse;
import com.konasl.livescore.dto.UserRequest;
import com.konasl.livescore.dto.UserResponse;
import com.konasl.livescore.exception.GlobalValidationException;
import com.konasl.livescore.service.UserService;
import com.konasl.livescore.validator.AuthValidator;
import com.konasl.livescore.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserValidator userValidator;
    private final AuthValidator authValidator;
    private final UserService userService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest, BindingResult result) {
        authValidator.validate(authRequest, result);
        if (result.hasErrors()) {
            throw new GlobalValidationException(getErrors(result));
        }
        return userService.login(authRequest);
    }

    @PostMapping("/registration")
    public ResponseEntity<UserResponse> registration(@RequestBody UserRequest userRequest, BindingResult result) {
        userValidator.validate(userRequest, result);
        if (result.hasErrors()) {
            throw new GlobalValidationException(getErrors(result));
        }
        return new ResponseEntity<>(userService.registration(userRequest), HttpStatus.OK);
    }

    private List<String> getErrors(BindingResult result) {
        return result.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
    }
}
