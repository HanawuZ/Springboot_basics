package com.example.springboot.auth;

import com.example.springboot.models.User;
import java.util.concurrent.TimeUnit;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
// import java.util.List;

@Component
public class JwtUtil implements Serializable{
    private final String secret_key = "mysecretkey";
    private long accessTokenValidity = 60*60*1000;
    private final Logger logger = LoggerFactory.getLogger(JwtUtil.class);


    private final JwtParser jwtParser;

    @Autowired
    private final JwtBuilder jwtBuilder;


    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";


    public JwtUtil(JwtBuilder jwtBuilder) {
        this.jwtParser = Jwts.parser().setSigningKey(secret_key);
        try {
            this.jwtBuilder = jwtBuilder;
        } catch (Exception e) {
            logger.error("Error creating JwtUtil bean:", e);
            throw e; // Rethrow the exception to prevent creating the bean
        }
    }

    // Create a JWT token
    public String createToken(User user) {
        
        // Set the claims, username, and expiration
        Claims claims = Jwts.claims().setSubject(user.getUsername());

        // Add firstnamer and lastname to the claims
        claims.put("firstName",user.getFirstName());
        claims.put("lastName",user.getLastName());

        // Define the token's expiration
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return jwtBuilder
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secret_key)
                .compact();
    }

    // Validate the JWT token
    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getUsername(Claims claims) {
        return claims.getSubject();
    }

}
