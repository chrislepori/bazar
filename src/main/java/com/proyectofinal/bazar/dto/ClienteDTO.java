package com.proyectofinal.bazar.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDTO {

    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;
    @NotNull(message = "El apellido no puede ser nulo")
    private String apellido;
    @NotNull(message = "El DNI no puede ser nulo")
    private String dni;
    @NotNull(message = "El domicilio no puede ser nulo")
    private String domicilio;


}
