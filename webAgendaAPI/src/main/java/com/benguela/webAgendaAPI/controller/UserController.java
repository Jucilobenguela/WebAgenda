package com.benguela.webAgendaAPI.controller;

import com.benguela.webAgendaAPI.dto.employeeDto.EmployeeRegisterDto;
import com.benguela.webAgendaAPI.dto.userDto.UserLoginDto;
import com.benguela.webAgendaAPI.dto.userDto.UserRegisterDto;
import com.benguela.webAgendaAPI.exception.ExistentEmployeeException;
import com.benguela.webAgendaAPI.exception.InvalidEmailException;
import com.benguela.webAgendaAPI.exception.InvalidPasswordException;
import com.benguela.webAgendaAPI.exception.NotFindEmailException;
import com.benguela.webAgendaAPI.model.Employee;
import com.benguela.webAgendaAPI.model.User;
import com.benguela.webAgendaAPI.segurity.AuthService;
import com.benguela.webAgendaAPI.segurity.TokenService;
import com.benguela.webAgendaAPI.service.interfac.EmployeeI;
import com.benguela.webAgendaAPI.service.interfac.UserServiceI;
import com.benguela.webAgendaAPI.util.convert.ConvertEmployee;
import com.benguela.webAgendaAPI.util.convert.ConvertUser;
import com.benguela.webAgendaAPI.util.error.Err;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
            User user = ((User) authService.authenticate(userLoginDto.getEmail(), userLoginDto.getPassword()).getPrincipal());
            String token = tokenService.generateToken(user.getEmail());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Authorization", "Bearer" + token);
            return ResponseEntity.ok().headers(responseHeaders).body("User authenticated");
        } catch ( Exception e) {
            return ResponseEntity.badRequest().body(Err.error("Error", 400, e.getMessage()));
        }

    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        try {
            userServiceI.isIdentityPassword(userRegisterDto.getPassword(),userRegisterDto.getRepeatPassword());
            User user = ConvertUser.convertUserDtoToUser(userRegisterDto);
            User userSaved = userServiceI.validateUserRegister(user);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(userSaved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(userSaved);
        } catch (NotFindEmailException | InvalidEmailException | InvalidPasswordException e) {
            return ResponseEntity.badRequest().body(Err.error("Error", 400, e.getMessage()));
        }
    }

} 