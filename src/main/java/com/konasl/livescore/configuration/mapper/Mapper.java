package com.konasl.livescore.configuration.mapper;

@FunctionalInterface
public interface Mapper<S, T> {
    T map(S source);
}
