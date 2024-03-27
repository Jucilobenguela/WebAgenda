package com.benguela.WebAgenda_API.controller;

import com.benguela.WebAgenda_API.dto.userDto.UserLoginDto;
import com.benguela.WebAgenda_API.dto.userDto.UserRegisterDto;
import com.benguela.WebAgenda_API.infra.exception.NotFindEmail;
import com.benguela.WebAgenda_API.infra.util.Convert;
import com.benguela.WebAgenda_API.model.User;
import com.benguela.WebAgenda_API.service.UserServiceI;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/user")
public class UserController {
    private User user;
    @Autowired
    UserServiceI userServiceI;
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginDto userDto){
         this.user = Convert.convertUserDtoToUser(userDto);
        userServiceI.validateUserDetails(user);
        return ResponseEntity.ok(userServiceI.authenticated(user));
    }
    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDto userRegisterDto){
        if(userServiceI.isIdentityPassword(userRegisterDto.getPassword(),userRegisterDto.getRepeatPassword())){
            this.user = Convert.convertUserDtoToUser(userRegisterDto);
            User userSaved;
            try {
                userSaved = userServiceI.validateUserRegister(user);
                URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(userSaved.getId())
                        .toUri();
                return ResponseEntity.created(location).body(userSaved);
            } catch (NotFindEmail e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
       return ResponseEntity.badRequest().body("password do not know");
    }
}
