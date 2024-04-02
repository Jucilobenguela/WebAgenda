package com.benguela.webAgendaAPI.service.implement;

import com.benguela.webAgendaAPI.exception.IllegalDateTimeException;
import com.benguela.webAgendaAPI.exception.InvalidPhoneNumberException;
import com.benguela.webAgendaAPI.model.Client;
import com.benguela.webAgendaAPI.repository.ClientRepository;
import com.benguela.webAgendaAPI.service.interfac.ClientServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClientServiceImpl implements ClientServiceI {
    @Autowired
    ClientRepository clientRepository;
    @Override
    public void validateDetails(Client client) throws InvalidPhoneNumberException, IllegalDateTimeException {
        if (isValidateNumber(client.getClientPhoneNumber())){
            throw new InvalidPhoneNumberException("Invalid phone number");
        }
        if (isCorrectDate(client.getDateTime())){
            throw new IllegalDateTimeException("Date and Time input are not valid");
        }
    }
    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    private Boolean isValidateNumber(String phoneNumber) {
        String regex = "\\([0-9]{2}\\) 9?[0-9]{4}-[0-9]{4}";
        return phoneNumber.matches(regex);
    }
    private Boolean isCorrectDate(Date date){
      return (date != null && date.before(new Date()));
        }
}
