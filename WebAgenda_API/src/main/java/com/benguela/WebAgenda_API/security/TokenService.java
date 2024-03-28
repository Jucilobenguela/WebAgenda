package com.benguela.WebAgenda_API.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.benguela.WebAgenda_API.infra.exception.JWTTokenException;
import com.benguela.WebAgenda_API.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(User user){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        }catch (JWTTokenException e){
            throw new JWTTokenException("Err in token generation ");
        }

    }
    public HttpHeaders responseTokenHeaders(User user){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer" + generateToken(user));
        return responseHeaders;

    }
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTTokenException e){
            throw new JWTTokenException("Err in token validation ");
        }
    }
    private Instant generateExpirationDate(){
        return ZonedDateTime.now(ZoneId.of("Europe/Lisbon"))
                .plusHours(1)
                .toInstant();
    }

    public String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer", "");
    }
    public void setTokenCookie(HttpServletResponse response, String token){
        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }
}
