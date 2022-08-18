package com.uwu.tas.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@ConditionalOnEnabledResourceChain
@EnableAsync
@RequiredArgsConstructor
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    private final Environment environment;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
//        String base = environment.getRequiredProperty("spring.mvc.servlet.path");
        httpSecurity
                //.anonymous().disable()
                .authorizeRequests()

                //The order of the rules matters and the more specific rules should go first.

                .antMatchers("/vendor/register/**", "/vendor/verify/**").permitAll()

                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/**").access("hasRole('ROLE_PUBLIC_USER')")
                .antMatchers("/vendor/**").access("hasAnyRole('ROLE_VENDOR')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

        httpSecurity.csrf().disable();
    }
}
