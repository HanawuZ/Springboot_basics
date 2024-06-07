package com.example.springboot.auth;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.JwtParser;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;

// import com.example.springboot.models.User;
// import java.util.Date;
// import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    private final String secretKey = "mysecretkey";
    private long accessTokenValidity = 60 * 60 * 1000;

    // private final JwtParser jwtParser;

    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    // public JwtUtil() {
    //     // this.jwtParser = Jwts.parserBuilder();
    // }
}
// private final String secret_key = "mysecretkey";
// private long accessTokenValidity = 60*60*1000;

// private final JwtParser jwtParser;

// public JwtUtil() {
// this.jwtParser = Jwts.parser().setSigningKey(secret_key);
// }

// public String createToken(User user){
// Claims claims = Jwts.claims().setSubject(user.getUsername());
// claims.put("firstName",user.getFirstName());
// claims.put("lastName",user.getLastName());
// Date tokenCreateTime = new Date();
// Date tokenValidity = new Date(tokenCreateTime.getTime() +
// TimeUnit.MINUTES.toMillis(accessTokenValidity));
// return Jwts.builder()
// .setClaims(claims)
// .setExpiration(tokenValidity)
// .signWith(SignatureAlgorithm.HS256, secret_key)
// .compact();
// }
// }
