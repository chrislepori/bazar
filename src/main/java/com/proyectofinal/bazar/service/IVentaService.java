package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ProductoVentaDTO;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    public List<Venta> getVentas();
    public void createVenta(Venta vent);
    public Venta findVenta(Long codigoVenta);
    public void deleteVenta(Long codigoVenta);
    public void editVenta(Long codigoVentaOriginal, Long codigoVentaNuevo, LocalDate fechaVenta, double total, List<Producto> productos, Cliente cliente);
    public List<Producto> productosDeUnaVenta( Long codigoVenta);
    public String getMontoYTotalVentasPorDia(LocalDate fecha);
    public ProductoVentaDTO obtenerVentaMayor();

}
