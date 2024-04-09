package com.benguela.webAgendaAPI.service.interfac;

import com.benguela.webAgendaAPI.exception.ExistentEmployeeException;
import com.benguela.webAgendaAPI.exception.InvalidPasswordException;
import com.benguela.webAgendaAPI.model.Employee;

public interface EmployeeI {
    Employee create(Employee employee) throws ExistentEmployeeException, InvalidPasswordException;
    Boolean isExistent(String name);

}
