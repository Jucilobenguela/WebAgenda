package com.benguela.webAgendaAPI.util.convert;

import com.benguela.webAgendaAPI.dto.serviceProvided.ServiceProvidedDto;
import com.benguela.webAgendaAPI.dto.serviceProvided.ServiceProvidedUpdateDto;
import com.benguela.webAgendaAPI.model.ServiceProvided;

public class ConvertServiceProvided {
    public static ServiceProvided ConvertServiceProvidedDtoToServiceProvided(ServiceProvidedDto serviceProvidedDto) {
        return new ServiceProvided(serviceProvidedDto.getName(), serviceProvidedDto.getPrice(), serviceProvidedDto.getTime());
    }

    public static ServiceProvided ConvertServiceProvidedUpdateDtoToServiceProvided(ServiceProvidedUpdateDto serviceProvidedUpdateDto) {
        return new ServiceProvided(serviceProvidedUpdateDto.getName(), serviceProvidedUpdateDto.getPrice(), serviceProvidedUpdateDto.getTime());
    }
}
