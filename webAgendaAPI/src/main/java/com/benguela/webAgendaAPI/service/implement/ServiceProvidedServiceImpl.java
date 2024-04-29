package com.benguela.webAgendaAPI.service.implement;

import com.benguela.webAgendaAPI.exception.ServiceProvidedException;
import com.benguela.webAgendaAPI.model.ServiceProvided;
import com.benguela.webAgendaAPI.repository.ServiceProvidedRepository;
import com.benguela.webAgendaAPI.service.interfac.ServiceProvidedServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceProvidedServiceImpl implements ServiceProvidedServiceI {
    @Autowired
    ServiceProvidedRepository serviceProvidedRepository;
    @Override
    public ServiceProvided saveServiceProvided(ServiceProvided serviceProvided) throws ServiceProvidedException {
       ServiceProvided serviceProvided1 = serviceProvidedRepository.findByName(serviceProvided.getName());
       if (serviceProvided1!=null){
      throw new ServiceProvidedException("Exist Service Provided");
       }
        return serviceProvidedRepository.save(serviceProvided);
    }

    @Override
    public List<ServiceProvided> getAllServiceProvided() throws ServiceProvidedException {
        List<ServiceProvided> serviceProvidedList = serviceProvidedRepository.findAll();
        if (serviceProvidedList.isEmpty()){
            throw new ServiceProvidedException("Don´t found Service Provided");
        }
       return serviceProvidedList;
    }

    @Override
    public void deleteServiceProvided(Long id) throws ServiceProvidedException {
        ServiceProvided serviceProvided = findByIdServiceProvided(id);
        serviceProvidedRepository.delete(serviceProvided);
    }

    @Override
    public ServiceProvided updateServiceProvided(ServiceProvided serviceProvided, Long id) throws ServiceProvidedException {
        ServiceProvided serviceProvidedDB = getServiceProvided(id);
        if(!serviceProvided.allPropertiesInitialized()){
            throw new ServiceProvidedException("Don´t exist data for to be update");
        }
        if(serviceProvided.getName()!= null){
            serviceProvidedDB.setName(serviceProvided.getName());
        }
        if(serviceProvided.getPrice()>0.0){
            serviceProvidedDB.setPrice(serviceProvided.getPrice());
        }
        if(serviceProvided.getTime()!= null){
            serviceProvidedDB.setTime(serviceProvided.getTime());
        }
        return serviceProvidedRepository.save(serviceProvidedDB);
    }

    @Override
    public ServiceProvided getServiceProvided(Long id) throws ServiceProvidedException {
        return findByIdServiceProvided(id);
    }
    private ServiceProvided findByIdServiceProvided(Long id) throws ServiceProvidedException {
        Optional<ServiceProvided> serviceProvidedOptional = serviceProvidedRepository.findById(id);
       return serviceProvidedOptional.orElseThrow(() -> new ServiceProvidedException(
                "Service Provided don´t found"));
    }
}
