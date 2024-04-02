package com.benguela.webAgendaAPI.util.convert;

import com.benguela.webAgendaAPI.dto.clientDto.ClientDto;
import com.benguela.webAgendaAPI.model.Client;
import jakarta.persistence.Convert;


@Convert
public class ConvertClient {
    public static Client convertClientDtoToClient(ClientDto clientDto) {
        return new Client(clientDto.getClientName(), clientDto.getClientPhoneNumber(),clientDto.getDateTime(),
                clientDto.getServices());
    }
}
