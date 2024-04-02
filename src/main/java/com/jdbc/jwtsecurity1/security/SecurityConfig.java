package com.jdbc.jwtsecurity1.security;

import com.jdbc.jwtsecurity1.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeHttpRequests().requestMatchers(POST,"/api/login/**", "/api/token/refresh/**").permitAll();
        http.authorizeHttpRequests().requestMatchers(GET, "/api/user/**").permitAll();
        http.authorizeHttpRequests().requestMatchers(POST, "/api/user/save").hasAuthority("ROLE_SUPER_ADMIN");
        http.authorizeHttpRequests().requestMatchers(PUT, "/api/{userId}/block/**").hasAuthority("ROLE_SUPER_ADMIN");
        http.authorizeHttpRequests().requestMatchers(PUT, "/api/{userId}/unblock/**").hasAuthority("ROLE_SUPER_ADMIN");
        http.authorizeHttpRequests().requestMatchers(DELETE, "/api/{userId}/delete/**").hasAuthority("ROLE_SUPER_ADMIN");
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.apply(CustomSecurityDetails.customDsl());
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
