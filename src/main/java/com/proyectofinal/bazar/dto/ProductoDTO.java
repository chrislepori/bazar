package com.proyectofinal.bazar.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDTO {

    @NotNull(message = "El nombre del producto no puede ser nulo")
    private String nombre;
    @NotNull(message = "El precio del producto no puede ser nulo")
    private Double precio;
    @NotNull(message = "El cantidad disponible del producto no puede ser nulo")
    private Integer cantidadDisponible;
    @NotNull(message = "El marca del producto no puede ser nulo")
    private String marca;


}
