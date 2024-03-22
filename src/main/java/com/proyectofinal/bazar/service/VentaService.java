package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ProductoVentaDTO;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.repository.IProductoRepository;
import com.proyectofinal.bazar.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class VentaService implements IVentaService {
    @Autowired
    private IVentaRepository ventaRepo;
    @Autowired
    private IProductoRepository productoRepo;


    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public void createVenta(Venta vent) {
        Venta ventaGuardada = ventaRepo.save(vent);
        for (Producto p : ventaGuardada.getListaProductos()) {
            p.descontarCantidad();
        }
        productoRepo.saveAll(ventaGuardada.getListaProductos());

    }

    @Override
    public Venta findVenta(Long codigoVenta) {
        return ventaRepo.findById(codigoVenta).orElse(null);
    }

    @Override
    public void deleteVenta(Long codigoVenta) {
        ventaRepo.deleteById(codigoVenta);

    }

    @Override
    public void editVenta(Long codigoVentaOriginal, Long codigoVentaNuevo, LocalDate fechaVenta, double total, List<Producto> productos, Cliente cliente) {
        Venta venta = this.findVenta(codigoVentaOriginal);
        if (venta != null) {
            venta.setCodigoVenta(codigoVentaNuevo);
            venta.setFechaVenta(fechaVenta);
            venta.setTotal(total);
            venta.setListaProductos(productos);
            venta.setUnCliente(cliente);
            this.createVenta(venta);
        }

    }

    @Override
    public List<Producto> productosDeUnaVenta(Long codigoVenta) {
        Venta venta = this.findVenta(codigoVenta);
        List<Producto> listaSegunVenta = null;
        if (venta != null) {
            listaSegunVenta = venta.getListaProductos();
        }
        return listaSegunVenta;

    }

    @Override
    public String getMontoYTotalVentasPorDia(LocalDate fecha) {
        List<Venta> ventas = ventaRepo.findByFechaVenta(fecha);
        double totalVentas = ventas.stream()
                .mapToDouble(Venta::getTotal)
                .sum();
        return "El monto total de ventas es " + Double.toString(totalVentas) + " y la cantidad de ventas es " + ventas.size();

    }

    @Override
    public ProductoVentaDTO obtenerVentaMayor() {
        ProductoVentaDTO pvd = new ProductoVentaDTO();
        List<Venta> listaVentas = this.getVentas();
        return listaVentas.stream()
                .max(Comparator.comparingDouble(Venta::obtenerMonto))
                .map(ProductoVentaDTO::buildProductoVentaDto)
                .orElse(null);

    }


}
