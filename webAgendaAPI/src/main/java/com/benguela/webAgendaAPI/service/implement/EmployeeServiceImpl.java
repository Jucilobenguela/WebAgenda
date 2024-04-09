package com.benguela.webAgendaAPI.service.implement;

import com.benguela.webAgendaAPI.exception.ExistentEmployeeException;
import com.benguela.webAgendaAPI.exception.InvalidPasswordException;
import com.benguela.webAgendaAPI.model.Employee;
import com.benguela.webAgendaAPI.repository.EmployeeRepository;
import com.benguela.webAgendaAPI.service.interfac.EmployeeI;
import com.benguela.webAgendaAPI.util.enums.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeI {
    private Employee employee;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Employee create(Employee employee) throws InvalidPasswordException, ExistentEmployeeException {
        if (isValidPassword(employee.getPassword())) {
            UserDetails userDataBase =  employeeRepository.findByEmployeeName(employee.getUsername());
            if (userDataBase != null) {
                throw new ExistentEmployeeException("The employee already exists");
            } else {
                this.employee = employee;
                this.employee.setRole(RoleEnum.USER);
                this.employee.setPassword(encodePassword(employee.getPassword()));
            }
        }
        return employeeRepository.save(this.employee);
    }

    private boolean isValidPassword(String password) throws InvalidPasswordException {
        String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,}";
        if (!password.matches(regex)){
            throw new InvalidPasswordException("Invalid Password!");
        }
        return true;
    }
    @Override
    public Boolean isExistent(String name) {
        Employee existingEmployee = employeeRepository.findByEmployeeName(name);
        return existingEmployee != null;
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
    private Boolean isSamePassword(String passwordLogin, String passwordDb){
        return passwordEncoder.matches(passwordLogin,passwordDb);
    }
}