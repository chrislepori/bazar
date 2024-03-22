package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.ProductoVentaDTO;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VentaController {
    @Autowired
    private IVentaService ventaServ;

    @GetMapping("/ventas/get")
    public List<Venta> getVentas() {
        return ventaServ.getVentas();
    }

    @PostMapping("venta/create")
    public void createVenta(@RequestBody Venta v) {
        ventaServ.createVenta(v);

    }

    @GetMapping("venta/find/{codigoVenta}")
    public Venta findVenta(@PathVariable Long codigoVenta) {
        return ventaServ.findVenta(codigoVenta);
    }

    @DeleteMapping("venta/delete/{codigoVenta}")
    public void deleteCliente(@PathVariable Long codigoVenta) {
        ventaServ.deleteVenta(codigoVenta);
    }

    @GetMapping("venta/traerProductos/{codigoVenta}")
    public List<Producto> productosDeUnaVenta(@PathVariable Long codigoVenta) {
        return ventaServ.productosDeUnaVenta(codigoVenta);
    }

    @PutMapping("/venta/edit/{codigoVentaOriginal}")
    public void editVenta(@PathVariable Long codigoVentaOriginal,
                          @RequestParam(required = false) Long codigoVenta,
                          @RequestParam(required = false) LocalDate fechaVenta,
                          @RequestParam(required = false) double total,
                          @RequestParam(required = false) List<Producto> listaProductos,
                          @RequestParam(required = false) Cliente unCliente) {
        ventaServ.editVenta(codigoVentaOriginal, codigoVenta, fechaVenta, total, listaProductos, unCliente);
    }
    @GetMapping("ventas/mayorVenta")
    public ProductoVentaDTO obtenerVentaMayor(){
        return ventaServ.obtenerVentaMayor();
    }


}
