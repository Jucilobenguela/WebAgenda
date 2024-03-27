package com.benguela.WebAgenda_API.infra.exception;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String message){
        super(message);
    }
}
