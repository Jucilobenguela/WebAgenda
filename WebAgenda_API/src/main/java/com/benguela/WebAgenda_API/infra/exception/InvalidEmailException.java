package com.benguela.WebAgenda_API.infra.exception;

public class InvalidEmailException extends Exception{
    public InvalidEmailException(String message){
        super(message);
    }
}
