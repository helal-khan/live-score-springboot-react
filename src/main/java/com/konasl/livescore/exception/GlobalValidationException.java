package com.konasl.livescore.exception;

import javax.validation.ValidationException;
import java.util.List;

public class GlobalValidationException extends ValidationException {
    public GlobalValidationException(List<String> message) {
        super(String.join("˜", message));
    }
}