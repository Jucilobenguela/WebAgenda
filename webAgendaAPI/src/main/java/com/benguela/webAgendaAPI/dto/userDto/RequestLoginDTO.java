package com.benguela.webAgendaAPI.dto.userDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RequestLoginDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

    public RequestLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

}
