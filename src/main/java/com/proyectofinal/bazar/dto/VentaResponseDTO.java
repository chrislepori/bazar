package com.proyectofinal.bazar.dto;

import com.proyectofinal.bazar.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VentaResponseDTO {

    private Long id;
    private LocalDate fechaVenta;
    private Double total;
    private Cliente cliente;
}
