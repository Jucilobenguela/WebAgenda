package com.benguela.webAgendaAPI.exception;

public class JWTTokenException extends RuntimeException {
    public JWTTokenException(String message){
        super(message);
    }
}
