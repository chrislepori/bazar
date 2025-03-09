package com.proyectofinal.bazar.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class VentaDTO {

    @NotEmpty(message = "El id del cliente no puede ser nulo ni vacio")
    private Long clienteId;
    @NotEmpty(message = "No puede haber un id de producto vacio ni nulo")
    private List<Long> productosIds;
}
