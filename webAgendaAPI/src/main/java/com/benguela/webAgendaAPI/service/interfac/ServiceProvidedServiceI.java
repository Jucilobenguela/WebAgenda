package com.benguela.webAgendaAPI.service.interfac;

import com.benguela.webAgendaAPI.exception.ServiceProvidedException;
import com.benguela.webAgendaAPI.model.ServiceProvided;

import java.util.List;

public interface ServiceProvidedServiceI {
     ServiceProvided save(ServiceProvided serviceProvided) throws ServiceProvidedException;
     List<ServiceProvided> getAll();

}
