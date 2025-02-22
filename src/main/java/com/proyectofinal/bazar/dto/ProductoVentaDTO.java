package com.proyectofinal.bazar.dto;

import com.proyectofinal.bazar.model.Venta;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductoVentaDTO {

    private Long codigoDeVenta;
    private double total;
    private int cantProductos;
    private String nombreCliente;
    private String apellidoCliente;


}
