package com.benguela.WebAgenda_API.infra.exception;

public class NotFindEmail extends Exception{
    private String message;
    public NotFindEmail(String message){
        super(message);
    }
}
