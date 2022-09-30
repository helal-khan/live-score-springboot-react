package com.konasl.livescore.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1329485186783166554L;

    public NotFoundException(String message) {
        super(message);
    }
}
