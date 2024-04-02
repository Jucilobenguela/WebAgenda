package com.benguela.webAgendaAPI.util.convert;

import com.benguela.webAgendaAPI.dto.userDto.UserLoginDto;
import com.benguela.webAgendaAPI.dto.userDto.UserRegisterDto;
import com.benguela.webAgendaAPI.model.User;
import org.springframework.stereotype.Component;

@Component
public class ConvertUser {
    public static User convertUserDtoToUser(UserRegisterDto userRegisterDto) {
        return new User(userRegisterDto.getName(), userRegisterDto.getEmail(), userRegisterDto.getPassword());
    }
    public static User convertUserDtoToUser(UserLoginDto userLoginDto) {
        return new User( userLoginDto.getEmail(), userLoginDto.getPassword());
    }
}
