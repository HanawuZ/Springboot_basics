package com.example.springboot.auth;

// import com.ey.springboot3security.service.UserInfoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.springboot.models.DataResult;
import com.example.springboot.models.User;
import com.example.springboot.services.JwtService;
import com.example.springboot.services.UserService;
import com.example.springboot.exception.GeneralErrorExcpetion;
import java.io.IOException;

// This class helps us to validate the generated jwt token
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {

            String authHeader = request.getHeader("Authorization");
            String token = null;
            String username = null;
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                username = jwtService.extractUsername(token);
            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                DataResult<User> result = userService.getUserData(username);
                if (result.getData() != null && jwtService.validateToken(token, result.getData())) {
                    User user = result.getData();

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user,
                            null, user.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            throw new GeneralErrorExcpetion(e.getMessage());
        }
    }
}
