package com.konasl.livescore.util;

public class AppConstants {

    public static final String DEFAULT_ROLE = "USER";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd hh:mm:ss a";
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    //Error messages
    public static final String VALIDATION_ERROR_MESSAGE = "Validation error";
    public static final String UNAUTHORIZED_ERROR_MESSAGE = "Invalid credentials";
    public static final String FULL_NAME_REQUIRED = "FullName is required";
    public static final String USERNAME_REQUIRED = "Username is required";
    public static final String USERNAME_TAKEN = "Username taken already";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String EMAIL_REQUIRED = "Email is required";
    public static final String INVALID_EMAIL = "Invalid email";
    public static final String EMAIL_EXISTS = "Email exists";
}