package com.benguela.webAgendaAPI.controller;

import com.benguela.webAgendaAPI.dto.clientDto.ClientDto;
import com.benguela.webAgendaAPI.exception.IllegalDateTimeException;
import com.benguela.webAgendaAPI.exception.InvalidPhoneNumberException;
import com.benguela.webAgendaAPI.model.Client;
import com.benguela.webAgendaAPI.service.interfac.ClientServiceI;
import com.benguela.webAgendaAPI.util.convert.ConvertClient;
import com.benguela.webAgendaAPI.util.error.Err;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/web_agenda/client")
@RestController
public class ClientController {
    @Autowired
    ClientServiceI clientServiceI;

    @PostMapping("/schedule")
    public ResponseEntity<?> scheduling(@RequestBody @Valid ClientDto clientDto){
        Client client = ConvertClient.convertClientDtoToClient(clientDto);
        try {
            clientServiceI.validateDetails(client);
            Client clientSaved = clientServiceI.save(client);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("{/id}")
                    .buildAndExpand(clientSaved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(clientSaved);
        } catch (InvalidPhoneNumberException | IllegalDateTimeException e) {
            return ResponseEntity.badRequest().body(Err.error("Error", 400, e.getMessage()));
        }
    }
}
