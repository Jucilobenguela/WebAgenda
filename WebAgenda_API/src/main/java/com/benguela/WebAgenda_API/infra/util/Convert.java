package com.benguela.WebAgenda_API.infra.util;


import com.benguela.WebAgenda_API.dto.userDto.UserDto;
import com.benguela.WebAgenda_API.dto.userDto.UserLoginDto;
import com.benguela.WebAgenda_API.dto.userDto.UserRegisterDto;
import com.benguela.WebAgenda_API.model.User;
import org.springframework.stereotype.Component;

@Component
public class Convert<T> {
    public static User convertUserDtoToUser(UserRegisterDto userRegisterDto) {
        return new User(userRegisterDto.getName(), userRegisterDto.getEmail(), userRegisterDto.getPassword());
    }
    public static User convertUserDtoToUser(UserLoginDto userLoginDto) {
        return new User( userLoginDto.getEmail(), userLoginDto.getPassword());
    }
}
