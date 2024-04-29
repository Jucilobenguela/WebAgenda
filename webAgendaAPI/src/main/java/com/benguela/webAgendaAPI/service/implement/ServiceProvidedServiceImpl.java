package com.benguela.webAgendaAPI.service.implement;

import com.benguela.webAgendaAPI.exception.ServiceProvidedException;
import com.benguela.webAgendaAPI.model.ServiceProvided;
import com.benguela.webAgendaAPI.repository.ServiceProvidedRepository;
import com.benguela.webAgendaAPI.service.interfac.ServiceProvidedServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public List<ServiceProvided> getAll() throws ServiceProvidedException {
        List<ServiceProvided> serviceProvidedList = serviceProvidedRepository.findAll();
        if (serviceProvidedList.isEmpty()){
            throw new ServiceProvidedException("Don´t found Service Provided");
        }
       return serviceProvidedList;
    }

    @Override
    public void deleteServiceProvided(Long id) throws ServiceProvidedException {
        Optional<ServiceProvided> serviceProvidedOptional = serviceProvidedRepository.findById(id);
        ServiceProvided serviceProvided = serviceProvidedOptional.orElseThrow(() -> new ServiceProvidedException(
                "Service Provided don´t found"));
        serviceProvidedRepository.delete(serviceProvided);

    }
}
