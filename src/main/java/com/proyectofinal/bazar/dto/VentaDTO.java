package com.proyectofinal.bazar.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class VentaDTO {

    @NotNull(message = "El id del cliente no puede ser nulo")
    private Long clienteId;
    @NotNull(message = "No puede haber un id de producto nulo")
    private List<Long> productosIds;
}
