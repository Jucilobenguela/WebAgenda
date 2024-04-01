package com.benguela.webAgendaAPI.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public abstract class  UserDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
