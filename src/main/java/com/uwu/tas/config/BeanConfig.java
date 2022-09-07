package com.uwu.tas.config;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@EnableCaching
@EnableScheduling
@EnableAsync
@Configuration
public class BeanConfig implements Jackson2ObjectMapperBuilderCustomizer {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void customize(Jackson2ObjectMapperBuilder builder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .withZone(ZoneId.of("GMT"));
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(formatter);

        builder.failOnEmptyBeans(false) // prevent InvalidDefinitionException Error
                .serializerByType(LocalDateTime.class, localDateTimeSerializer);
    }
}
