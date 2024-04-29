package com.benguela.webAgendaAPI.service.interfac;

import com.benguela.webAgendaAPI.exception.ServiceProvidedException;
import com.benguela.webAgendaAPI.model.ServiceProvided;

import java.util.List;

public interface ServiceProvidedServiceI {
     ServiceProvided saveServiceProvided(ServiceProvided serviceProvided) throws ServiceProvidedException;
     List<ServiceProvided> getAllServiceProvided() throws ServiceProvidedException;
     void deleteServiceProvided(Long id) throws ServiceProvidedException;
     ServiceProvided updateServiceProvided(ServiceProvided serviceProvided, Long id) throws ServiceProvidedException;
     ServiceProvided getServiceProvided(Long id) throws ServiceProvidedException;

}
