package com.benguela.WebAgenda_API.infra.exception;

public class NotFindEmailException extends Exception{
    public NotFindEmailException(String message){
        super(message);
    }
}
