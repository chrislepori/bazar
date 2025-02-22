package com.proyectofinal.bazar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoResponseDTO {

    private Long id;
    private String nombre;
    private Double costo;
    private Integer cantidadDisponible;
    private String marca;


}
