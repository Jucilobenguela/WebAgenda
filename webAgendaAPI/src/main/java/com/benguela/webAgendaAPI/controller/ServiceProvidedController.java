package com.benguela.webAgendaAPI.controller;

import com.benguela.webAgendaAPI.dto.serviceProvided.ServiceProvidedDto;
import com.benguela.webAgendaAPI.exception.ServiceProvidedException;
import com.benguela.webAgendaAPI.model.ServiceProvided;
import com.benguela.webAgendaAPI.service.interfac.ServiceProvidedServiceI;
import com.benguela.webAgendaAPI.util.convert.ConvertServiceProvided;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("web_agenda/service")
public class ServiceProvidedController {
    @Autowired
    ServiceProvidedServiceI serviceProvidedServiceI;
    @GetMapping
    public ResponseEntity<List<ServiceProvided>> getAll(){
        return ResponseEntity.ok().body(serviceProvidedServiceI.getAll());
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerService( @RequestBody ServiceProvidedDto serviceProvidedDto){
        try {
            ServiceProvided serviceProvided = ConvertServiceProvided.ConvertServiceProvidedDtoToServiceProvided(serviceProvidedDto);
            ServiceProvided serviceProvidedSaved = serviceProvidedServiceI.save(serviceProvided);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(serviceProvided.getId())
                    .toUri();
            return ResponseEntity.created(location).body(serviceProvidedSaved);
        } catch (ServiceProvidedException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
