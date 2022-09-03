package com.code.loganalyzer.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigs {

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
