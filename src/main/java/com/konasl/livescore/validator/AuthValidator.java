package com.konasl.livescore.validator;

import com.konasl.livescore.dto.AuthRequest;
import com.konasl.livescore.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AuthValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(AuthRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AuthRequest authRequest = (AuthRequest) target;

        if (StringUtils.isBlank(authRequest.getUsername())) {
            errors.rejectValue("username", "username.is.required", AppConstants.USERNAME_REQUIRED);
        }
        if (StringUtils.isBlank(authRequest.getPassword())) {
            errors.rejectValue("password", "password.is.required", AppConstants.PASSWORD_REQUIRED);
        }
    }

}
