package com.example.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.springboot.auth.JwtUtil;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Configuration
public class JwtConfig  {

    @Bean
    public JwtBuilder jwtBuilder() {
        return Jwts.builder();
    }

    // @Bean
    // public JwtParser jwtParser() {
    //     return Jwts.parser();
    // }

    @Bean
    public JwtUtil jwtUtil(JwtBuilder jwtBuilder) {
        return new JwtUtil(jwtBuilder);
    }

    
}

