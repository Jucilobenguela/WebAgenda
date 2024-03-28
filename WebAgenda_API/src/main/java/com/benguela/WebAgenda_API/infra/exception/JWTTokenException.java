package com.benguela.WebAgenda_API.infra.exception;

public class JWTTokenException extends RuntimeException {
    public JWTTokenException(String message){
        super(message);
    }
}
