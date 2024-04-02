package com.jdbc.jwtsecurity1.security;

import com.jdbc.jwtsecurity1.filter.CustomAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

    public class CustomSecurityDetails extends AbstractHttpConfigurer<CustomSecurityDetails, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            CustomAuthenticationFilter filter = new CustomAuthenticationFilter(authenticationManager);
            filter.setFilterProcessesUrl("/api/login");
            http.addFilter(filter);
        }

        public static CustomSecurityDetails customDsl() {
            return new CustomSecurityDetails();
        }
    }
