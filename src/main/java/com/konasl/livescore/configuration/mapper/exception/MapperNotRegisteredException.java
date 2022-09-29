package com.konasl.livescore.configuration.mapper.exception;

import lombok.Getter;

@Getter
public class MapperNotRegisteredException extends RuntimeException {
    private final Class<?> sourceClass;
    private final Class<?> targetClass;

    public MapperNotRegisteredException(Class<?> sourceClass, Class<?> targetClass) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    @Override
    public String getMessage() {
        return "No Mapper registered for source class: " + sourceClass.getSimpleName() + " and target class: " + targetClass.getSimpleName();
    }
}
