package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {
    @Autowired
    private IProductoService productoServ;

    @GetMapping("/productos/get")
    public List<Producto> getProductos() {
        return productoServ.getProductos();
    }

    @PostMapping("producto/create")
    public void createProducto(@RequestBody Producto p) {
        productoServ.createProducto(p);

    }

    @GetMapping("producto/find/{codigoProducto}")
    public Producto findProducto(@PathVariable Long codigoProducto) {
        return productoServ.findProducto(codigoProducto);
    }

    @DeleteMapping("producto/delete/{codigoProducto}")
    public void deleteProducto(@PathVariable Long codigoProducto) {
        productoServ.deleteProducto(codigoProducto);
    }

    @GetMapping("productos/faltaStock")
    public List<Producto> cantidadProductosMenoresCinco() {
        return productoServ.cantidadProductosMenoresCinco();
    }

    @PutMapping("/producto/edit/{codigoProductoOriginal}")
    public void editProducto(@PathVariable Long codigoProductoOriginal,
                             @RequestParam(required = false) Long codigoProducto,
                             @RequestParam(required = false) String nombre,
                             @RequestParam(required = false) String marca,
                             @RequestParam(required = false) double costo,
                             @RequestParam(required = false) double cantidadDisponible) {
        productoServ.editProducto(codigoProductoOriginal,codigoProducto,nombre,marca,costo,cantidadDisponible);

    }


}
