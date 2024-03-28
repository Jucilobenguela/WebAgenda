package com.benguela.WebAgenda_API.controller;

import com.benguela.WebAgenda_API.dto.userDto.UserLoginDto;
import com.benguela.WebAgenda_API.dto.userDto.UserRegisterDto;
import com.benguela.WebAgenda_API.infra.exception.InvalidEmailException;
import com.benguela.WebAgenda_API.infra.exception.InvalidPasswordException;
import com.benguela.WebAgenda_API.infra.exception.NotFindEmailException;
import com.benguela.WebAgenda_API.infra.util.Convert;
import com.benguela.WebAgenda_API.model.User;
import com.benguela.WebAgenda_API.security.TokenService;
import com.benguela.WebAgenda_API.service.UserServiceI;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*"
        , methods = {
        RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
        , allowedHeaders = {"Content-Type", "Authorization"})
public class UserController {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserServiceI userServiceI;
    @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDto loginDTO){
        Authentication userNamePassword
                = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword());
        Authentication auth = this.authenticationManager.authenticate(userNamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", "Bearer" + token);
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body("teste");
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
