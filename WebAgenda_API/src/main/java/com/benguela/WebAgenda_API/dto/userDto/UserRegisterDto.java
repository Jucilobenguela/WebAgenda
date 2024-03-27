package com.benguela.WebAgenda_API.dto.userDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class UserRegisterDto extends UserDto{
    @NotBlank
    private String name;
    @NotBlank
    private String repeatPassword;
   // @NotBlank
  //  private RoleEnum roleEnum;

    public String getName() {
        return name;
    }
    public String getRepeatPassword() {
        return repeatPassword;
    }
}
