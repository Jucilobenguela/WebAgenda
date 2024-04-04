package com.benguela.webAgendaAPI.util.error;

import java.util.HashMap;
import java.util.Map;

public class Err {
    public static Map<String, Object> error(String status, int code, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("status", status);
        body.put("code", code);
        body.put("message", message);
        return body;
    }
}
