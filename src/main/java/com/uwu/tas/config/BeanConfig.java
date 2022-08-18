package com.uwu.tas.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableAsync
@Configuration
public class BeanConfig implements Jackson2ObjectMapperBuilderCustomizer {


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        builder.failOnEmptyBeans(false); // prevent InvalidDefinitionException Error
    }
}
