package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Producto;
import java.util.List;

public interface IProductoService {
    public List<Producto> getProductos();

    public void createProducto(Producto producto);

    public Producto findProducto(Long codigoProducto);

    public void deleteProducto(Long codigoProducto);

    public void editProducto(Long codigoProductoOriginal, Long codigoProducto, String nombre, String marca, double costo, double cantidadDisponible);
    public List<Producto> cantidadProductosMenoresCinco();


}

