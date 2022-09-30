package com.konasl.livescore.validator;

import com.konasl.livescore.dto.UserRequest;
import com.konasl.livescore.repository.UserRepository;
import com.konasl.livescore.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserRepository userRepository;
    Pattern pattern = Pattern.compile(AppConstants.EMAIL_PATTERN);

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserRequest.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRequest userRequest = (UserRequest) target;

        if (StringUtils.isBlank(userRequest.getFullName())) {
            errors.rejectValue("fullName", "fullName.is.required", AppConstants.FULL_NAME_REQUIRED);
        }
        if (StringUtils.isBlank(userRequest.getUsername())) {
            errors.rejectValue("username", "username.is.required", AppConstants.USERNAME_REQUIRED);
        }
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            errors.rejectValue("username", "username.is.taken", AppConstants.USERNAME_TAKEN);
        }
        if (StringUtils.isBlank(userRequest.getPassword())) {
            errors.rejectValue("password", "password.is.required", AppConstants.PASSWORD_REQUIRED);
        }
        if (StringUtils.isBlank(userRequest.getEmail())) {
            errors.rejectValue("email", "email.is.required", AppConstants.EMAIL_REQUIRED);
        }
        if (!pattern.matcher(userRequest.getEmail()).matches()) {
            errors.rejectValue("email", "email.is.invalid", AppConstants.INVALID_EMAIL);
        }
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            errors.rejectValue("email", "email.is.exist", AppConstants.EMAIL_EXISTS);
        }
    }
}
