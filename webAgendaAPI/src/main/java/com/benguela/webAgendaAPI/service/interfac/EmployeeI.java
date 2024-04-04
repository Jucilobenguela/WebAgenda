package com.benguela.webAgendaAPI.service.interfac;

import com.benguela.webAgendaAPI.exception.ExistentEmployeeException;
import com.benguela.webAgendaAPI.model.Employee;

public interface EmployeeI {
    Employee create(Employee employee) throws ExistentEmployeeException;
    Boolean isExistent(String name);

}
