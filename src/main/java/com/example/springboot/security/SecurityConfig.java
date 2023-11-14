package com.example.springboot.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.example.springboot.auth.JwtUtil;

// import com.example.springboot.auth.JwtAuthorizationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService( new UserDetailsServiceImpl()).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // http.csrf().disable()
        //         .authorizeRequests()
        //         .requestMatchers("/rest/auth/**").permitAll()
        //         .anyRequest().authenticated()
        //         .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.headers(header ->
            header.frameOptions(frameOptionsConfig ->
                frameOptionsConfig.sameOrigin()
            )
        ).csrf(csrf ->csrf.disable())
        .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> 
            authorize.requestMatchers("/h2-console/**").permitAll()
                    // Permit all requests to the /admin/signup endpoint
                    .requestMatchers("/admin/signup").permitAll()                    
                    .requestMatchers("/admin/signin").permitAll()                            
                    // .requestMatchers("/admin/authenticated").permitAll()
                    .anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    

    // @Bean
    // public DaoAuthenticationProvider daoAuthenticationProvider() {
    //     DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    //     daoAuthenticationProvider.setUserDetailsService(userDetailsService());
    //     daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    //     return daoAuthenticationProvider;
    // }
}