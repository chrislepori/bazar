package com.proyectofinal.bazar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class VentaDTO {

    private Long clienteId;
    private List<Long> productosIds;
}
