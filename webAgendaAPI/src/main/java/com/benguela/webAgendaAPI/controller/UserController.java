package com.benguela.webAgendaAPI.controller;

import com.benguela.webAgendaAPI.dto.userDto.UserLoginDto;
import com.benguela.webAgendaAPI.dto.userDto.UserRegisterDto;
import com.benguela.webAgendaAPI.exception.InvalidEmailException;
import com.benguela.webAgendaAPI.exception.InvalidPasswordException;
import com.benguela.webAgendaAPI.exception.NotAuthenticateException;
import com.benguela.webAgendaAPI.exception.NotFindEmailException;
import com.benguela.webAgendaAPI.model.User;
import com.benguela.webAgendaAPI.segurity.AuthService;
import com.benguela.webAgendaAPI.segurity.TokenService;
import com.benguela.webAgendaAPI.service.UserServiceI;
import com.benguela.webAgendaAPI.util.Convert;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/web_agenda/user")
public class UserController {

    @Autowired
    UserServiceI userServiceI;
    @Autowired
    TokenService tokenService;
    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid UserLoginDto userLoginDto){
        try {
            String token = tokenService.generateToken((User) authService
                    .authenticate(userLoginDto.getEmail(), userLoginDto.getPassword()).getPrincipal());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", "Bearer" + token);
            return ResponseEntity.ok().headers(responseHeaders).body("User authenticated");
        } catch ( Exception e) {
            return ResponseEntity.badRequest().body(error("Error", 400, e.getMessage()));
        }

    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        try {
            userServiceI.isIdentityPassword(userRegisterDto.getPassword(),userRegisterDto.getRepeatPassword());
            User user = Convert.convertUserDtoToUser(userRegisterDto);
            User userSaved;
            userSaved = userServiceI.validateUserRegister(user);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(userSaved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(userSaved);
        } catch (NotFindEmailException | InvalidEmailException | InvalidPasswordException e) {
            return ResponseEntity.badRequest().body(error("Error", 400, e.getMessage()));
        }
    }
    private Map<String, Object> error(String status, int code, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("status", status);
        body.put("code", code);
        body.put("message", message);
        return body;
    }

} 