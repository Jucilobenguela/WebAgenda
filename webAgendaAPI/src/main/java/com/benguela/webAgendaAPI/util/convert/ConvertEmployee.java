package com.benguela.webAgendaAPI.util.convert;

import com.benguela.webAgendaAPI.dto.employeeDto.EmployeeLoginDto;
import com.benguela.webAgendaAPI.dto.employeeDto.EmployeeRegisterDto;
import com.benguela.webAgendaAPI.model.Employee;

public class ConvertEmployee {
    public static Employee ConvertEmployeeDtoToEmployee(EmployeeRegisterDto employeeRegisterDto) {
        return new Employee(employeeRegisterDto.getEmployeeName(), employeeRegisterDto.getPassword());
    }
    public static Employee ConvertEmployeeDtoToEmployee(EmployeeLoginDto employeeLoginDto) {
        return new Employee(employeeLoginDto.getEmployeeName(), employeeLoginDto.getPassword());
    }
}
