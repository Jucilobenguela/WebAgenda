package com.benguela.webAgendaAPI.dto.serviceProvided;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ServiceProvidedUpdateDto {

    private String name;

    private double price;

    private String time;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }
}
