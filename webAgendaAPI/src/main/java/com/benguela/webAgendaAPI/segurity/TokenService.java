package com.benguela.webAgendaAPI.segurity;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.benguela.webAgendaAPI.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Service
public class TokenService {
    //@Value("${api.security.token.secret}")
    private String secret="secret";

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
           return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token).getSubject();
        } catch (JWTVerificationException exception){
            return "tratar essa exception";
        }
    }
    private Instant genExpirationDate() {
        return ZonedDateTime.now(ZoneId.of("Europe/Lisbon"))
                .plusHours(1)
                .toInstant();
    }
    public  void setTokenCookie(String token, HttpServletResponse response){
        Cookie cookie = new Cookie("jwtToken", token);
        cookie.setPath("/");
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(21600);
        response.addCookie(cookie);
    }
}