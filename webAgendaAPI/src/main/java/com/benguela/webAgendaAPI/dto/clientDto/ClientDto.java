package com.benguela.webAgendaAPI.dto.clientDto;

import com.benguela.webAgendaAPI.util.enums.ServiceEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class ClientDto {
    @NotBlank
    private String clientName;
    @NotBlank
    private String clientPhoneNumber;
   // private Service service;

    @DateTimeFormat
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateTime;
    @NotEmpty
    private List<ServiceEnum> services;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public List<ServiceEnum> getServices() {
        return services;
    }

    public void setServices(List<ServiceEnum> services) {
        this.services = services;
    }
}
