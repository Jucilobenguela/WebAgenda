package com.benguela.webAgendaAPI.model;

import com.benguela.webAgendaAPI.util.enums.ServiceEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    private String clientName;
    @NotBlank
    private String clientPhoneNumber;

    @DateTimeFormat
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateTime;
    @NotEmpty
    private List<ServiceEnum> service;

    public Client(String clientName, String clientPhoneNumber, Date dateTime, List<ServiceEnum> service) {
        this.clientName = clientName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.dateTime = dateTime;
        this.service = service;
    }
    public Client(){

    }

    public Long getId() {
        return this.id;
    }


    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }


    public Date getDateTime() {
        return dateTime;
    }

    public List<ServiceEnum> getService() {
        return service;
    }

    public void setService(List<ServiceEnum> service) {
        this.service = service;
    }
}
