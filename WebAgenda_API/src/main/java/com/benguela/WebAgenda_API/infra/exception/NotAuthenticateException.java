package com.benguela.WebAgenda_API.infra.exception;

public class NotAuthenticateException extends Exception{
    public NotAuthenticateException(String message){
        super(message);
    }
}
