package com.benguela.webAgendaAPI.service.implement;

import com.benguela.webAgendaAPI.exception.ServiceProvidedException;
import com.benguela.webAgendaAPI.model.ServiceProvided;
import com.benguela.webAgendaAPI.repository.ServiceProvidedRepository;
import com.benguela.webAgendaAPI.service.interfac.ServiceProvidedServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class ServiceProvidedServiceImpl implements ServiceProvidedServiceI {
    @Autowired
    ServiceProvidedRepository serviceProvidedRepository;
    @Override
    public ServiceProvided save(ServiceProvided serviceProvided) throws ServiceProvidedException {
       ServiceProvided serviceProvided1 = serviceProvidedRepository.findByName(serviceProvided.getName());
       if (serviceProvided1!=null){
      throw new ServiceProvidedException("Exist Service Provided");
       }
        return serviceProvidedRepository.save(serviceProvided);
    }

    @Override
    public List<ServiceProvided> getAll()  {
        return null;
    }
}
