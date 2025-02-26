package com.inn.products.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.inn.products.filters.TokenIdFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).httpBasic(basic -> basic.disable()).formLogin(login -> login.disable())
                .addFilterBefore(new TokenIdFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                        .anyRequest().permitAll());
      return http.build();
    }
}