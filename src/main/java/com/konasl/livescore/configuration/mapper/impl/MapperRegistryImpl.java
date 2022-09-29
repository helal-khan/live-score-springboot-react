package com.konasl.livescore.configuration.mapper.impl;


import com.konasl.livescore.configuration.mapper.Mapper;
import com.konasl.livescore.configuration.mapper.MapperRegistry;
import com.konasl.livescore.configuration.mapper.exception.MapperAlreadyRegisteredException;
import com.konasl.livescore.configuration.mapper.exception.MapperNotRegisteredException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class MapperRegistryImpl implements MapperRegistry {

    private final Map<ClassPair<?, ?>, Mapper<?, ?>> mappers = new HashMap<>();

    @Override
    public <S, T> Mapper<S, T> getMapper(Class<S> sourceClass, Class<T> targetClass) {
        Mapper<?, ?> mapper = mappers.get(new ClassPair<>(sourceClass, targetClass));
        if (mapper == null) {
            throw new MapperNotRegisteredException(sourceClass, targetClass);
        }
        //noinspection unchecked
        return (Mapper<S, T>) mapper;
    }

    @Override
    public <S, T> void addMapper(Class<S> sourceClass, Class<T> targetClass, Mapper<S, T> mapper) {
        ClassPair<S, T> classPair = new ClassPair<>(sourceClass, targetClass);
        Mapper<?, ?> existingMapper = mappers.get(classPair);
        if (existingMapper != null) {
            throw new MapperAlreadyRegisteredException(sourceClass, targetClass);
        }
        mappers.put(classPair, mapper);
    }

    private static class ClassPair<S, T> {
        private final Class<S> sourceClass;
        private final Class<T> targetClass;

        public ClassPair(Class<S> sourceClass, Class<T> targetClass) {
            this.sourceClass = sourceClass;
            this.targetClass = targetClass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ClassPair<?, ?> classPair = (ClassPair<?, ?>) o;
            return sourceClass.equals(classPair.sourceClass) &&
                    targetClass.equals(classPair.targetClass);
        }

        @Override
        public int hashCode() {
            return Objects.hash(sourceClass, targetClass);
        }
    }
}
