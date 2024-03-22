package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private IProductoRepository productoRepo;

    @Override
    public List<Producto> getProductos() {
        return productoRepo.findAll();
    }

    @Override
    public void createProducto(Producto producto) {
        productoRepo.save(producto);


    }

    @Override
    public Producto findProducto(Long codigoProducto) {
        return productoRepo.findById(codigoProducto).orElse(null);
    }

    @Override
    public void deleteProducto(Long codigoProducto) {
        productoRepo.deleteById(codigoProducto);

    }

    @Override
    public void editProducto(Long codigoProductoOriginal, Long codigoProducto, String nombre, String marca, double costo, double cantidadDisponible) {
        Producto producto = this.findProducto(codigoProductoOriginal);
        if (producto != null) {
            producto.setCodigoProducto(codigoProducto);
            producto.setNombre(nombre);
            producto.setMarca(marca);
            producto.setCosto(costo);
            producto.setCantidadDisponible(cantidadDisponible);
            this.createProducto(producto);
        }

    }

    @Override
    public List<Producto> cantidadProductosMenoresCinco() {
        return productoRepo.findByCantidadDisponibleLessThanEqual(5);
    }


}
