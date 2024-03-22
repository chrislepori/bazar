package com.proyectofinal.bazar.dto;

import com.proyectofinal.bazar.model.Venta;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoVentaDTO {
    private Long codigoDeVenta;
    private double total;
    private int cantProductos;
    private String nombreCliente;
    private String apellidoCliente;

    public ProductoVentaDTO() {
    }

    public ProductoVentaDTO(Long codigoDeVenta, double total, int cantProductos, String nombreCliente, String apellidoCliente) {
        this.codigoDeVenta = codigoDeVenta;
        this.total = total;
        this.cantProductos = cantProductos;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
    }
    public static ProductoVentaDTO buildProductoVentaDto(Venta venta){
        return new ProductoVentaDTO(venta.getCodigoVenta(),venta.obtenerMonto(),venta.getListaProductos().size(),venta.getUnCliente().getNombre(),venta.getUnCliente().getApellido());
    }
}
