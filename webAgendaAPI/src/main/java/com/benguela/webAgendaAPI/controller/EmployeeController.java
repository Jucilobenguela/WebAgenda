package com.benguela.webAgendaAPI.controller;

import com.benguela.webAgendaAPI.dto.employeeDto.EmployeeRegisterDto;
import com.benguela.webAgendaAPI.exception.ExistentEmployeeException;
import com.benguela.webAgendaAPI.model.Employee;
import com.benguela.webAgendaAPI.service.interfac.EmployeeI;
import com.benguela.webAgendaAPI.util.convert.ConvertEmployee;
import com.benguela.webAgendaAPI.util.error.Err;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("web_agenda/employee")
public class EmployeeController {
    @Autowired
    EmployeeI employeeI;

    @PostMapping("/create")
    public ResponseEntity<?> createEmployeeToLogin(@RequestBody @Valid EmployeeRegisterDto employeeRegisterDto) {
        Employee employee = ConvertEmployee.ConvertEmployeeDtoToEmployee(employeeRegisterDto);
        try {
            Employee employeeSaved = employeeI.create(employee);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(employeeSaved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(employeeSaved);
        } catch (Exception  e) {
            return ResponseEntity.badRequest().body(Err.error("Error", 400, e.getMessage()));
        }
    }
}
