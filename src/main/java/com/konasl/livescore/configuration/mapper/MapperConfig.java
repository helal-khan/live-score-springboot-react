package com.konasl.livescore.configuration.mapper;

import com.konasl.livescore.configuration.mapper.impl.MapperRegistryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public MapperRegistry mapperRegistry() {
        return new MapperRegistryImpl();
    }
}
