package com.benguela.WebAgenda_API.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @PostMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok().body("teste");
    }
}
