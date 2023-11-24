package com.example.springilmiofotoalbum.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // le rotte /categories, /borrowings e /users solo per ADMIN
        http
                .authorizeHttpRequests()
                .requestMatchers("/categories").hasAuthority("ADMIN")
                .requestMatchers("/users").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/photos/**").hasAuthority("ADMIN")
                .requestMatchers("/photos", "/photos/**").hasAnyAuthority("ADMIN", "USER")
                .requestMatchers("/**").permitAll()
                .and().formLogin()
                .and().logout();
        http.csrf().disable();
        return http.build();
    }
}
