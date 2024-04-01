package com.benguela.webAgendaAPI.exception;

import java.io.IOException;

public class SecurityFilterException extends IOException {
    public SecurityFilterException(String message){
        super(message);
    }
}
