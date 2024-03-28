package com.benguela.WebAgenda_API.infra.exception;

import java.io.IOException;

public class SecurityFilterException extends IOException {
    public SecurityFilterException(String message){
        super(message);
    }
}
