package com.benguela.webAgendaAPI.dto.serviceProvided;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ServiceProvidedDto {
    @NotBlank
    private String name;
    @NotNull
    private double price;
    @NotBlank
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
