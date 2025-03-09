package com.proyectofinal.bazar.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VentaPaginationDTO {

    @NotNull(message = "El numero de pagina no puede ser nulo")
    private int numeroPagina;
    private int cantidadElementos = 10;
}
