package com.benguela.webAgendaAPI.service.implement;

import com.benguela.webAgendaAPI.exception.ExistentEmployeeException;
import com.benguela.webAgendaAPI.model.Employee;
import com.benguela.webAgendaAPI.repository.EmployeeRepository;
import com.benguela.webAgendaAPI.service.interfac.EmployeeI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeI {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) throws ExistentEmployeeException {
        if (!isExistent(employee.getName())) {
            return employeeRepository.save(employee);
        }
        throw new ExistentEmployeeException("The employee already exists.");
    }

    @Override
    public Boolean isExistent(String name) {
        Employee existingEmployee = employeeRepository.findByName(name);
        return existingEmployee != null;
    }

    private Employee findEmployee(String name) throws ExistentEmployeeException {
        Employee employee = employeeRepository.findByName(name);
      if (employee == null){
          throw new ExistentEmployeeException("Employee not fond");
      }
      else {
          return employee;
      }
    }
}