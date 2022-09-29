package com.konasl.livescore.configuration.mapper.exception;

import lombok.Getter;

@Getter
public class MapperAlreadyRegisteredException extends RuntimeException {
    private final Class<?> sourceClass;
    private final Class<?> targetClass;

    public MapperAlreadyRegisteredException(Class<?> sourceClass, Class<?> targetClass) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    @Override
    public String getMessage() {
        return "Mapper already registered for source class: " + sourceClass.getSimpleName() + " and target class: " + targetClass.getSimpleName();
    }

}
