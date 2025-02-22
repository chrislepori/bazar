package com.proyectofinal.bazar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoDTO {

    private String nombre;
    private Double precio;
    private Integer cantidadDisponible;
    private String marca;


}
