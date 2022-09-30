package com.konasl.livescore.exception;

import com.konasl.livescore.dto.ErrorResponse;
import com.konasl.livescore.entity.BaseEntity;
import com.konasl.livescore.entity.LiveScore;
import com.konasl.livescore.entity.User;
import com.konasl.livescore.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    private final Map<Class<? extends BaseEntity>, HttpStatus> notFoundErrorCodes;

    public AppExceptionHandler() {
        this.notFoundErrorCodes = new HashMap<>();
        this.notFoundErrorCodes.put(LiveScore.class, HttpStatus.NOT_FOUND);
        this.notFoundErrorCodes.put(User.class, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ErrorResponse> handleEntityNotFoundException(final EntityNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder().code(status.value()).status(notFoundErrorCodes.get(ex.getClazz()).name()).message(ex.getMessage()).build());

    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder().code(status.value()).status(status.name()).message(ex.getMessage()).build());
    }

    @ExceptionHandler(GlobalValidationException.class)
    ResponseEntity<ErrorResponse> handleValidationException(GlobalValidationException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .code(status.value())
                        .status(status.name())
                        .message(AppConstants.VALIDATION_ERROR_MESSAGE)
                        .errors(Arrays.asList(ex.getMessage().split("˜")))
                        .build());

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .code(status.value())
                        .status(status.name())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .code(status.value())
                        .status(status.name())
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .code(status.value())
                        .status(status.name())
                        .message(ex.getMessage())
                        .build());
    }
}
