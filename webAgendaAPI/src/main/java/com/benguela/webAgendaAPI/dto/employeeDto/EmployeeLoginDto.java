package com.benguela.webAgendaAPI.dto.employeeDto;

import jakarta.validation.constraints.NotBlank;

public class EmployeeLoginDto {
    @NotBlank
    private String employeeName;
    @NotBlank
    private String password;

    public String getEmployeeName() {
        return employeeName;
    }

    public String getPassword() {
        return password;
    }
}
