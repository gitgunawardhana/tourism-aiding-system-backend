package com.uwu.tas.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
@EnableAsync
@RequiredArgsConstructor
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/user/verify/**",
                        "/user/register/**",
                        "/vendor/verify/**",
                        "/vendor/register/**",
                        "/admin/create/**",
                        "/public-user/**")
                .permitAll()

                .antMatchers("/user/**")
                .access("hasRole('ROLE_PUBLIC_USER')")

                .antMatchers("/vendor/**")
                .access("hasAnyRole('ROLE_VENDOR', 'ROLE_ADMIN')")

                .antMatchers("/admin/**")
                .access("hasAnyRole('ROLE_ADMIN')")

                .and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
