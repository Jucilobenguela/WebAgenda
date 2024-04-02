package com.benguela.webAgendaAPI.service.interfac;

import com.benguela.webAgendaAPI.exception.IllegalDateTimeException;
import com.benguela.webAgendaAPI.exception.InvalidPhoneNumberException;
import com.benguela.webAgendaAPI.model.Client;
import org.springframework.stereotype.Service;

@Service
public interface ClientServiceI {
    void validateDetails(Client client) throws InvalidPhoneNumberException, IllegalDateTimeException;
    Client save(Client client);
}
