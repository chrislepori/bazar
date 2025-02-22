package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ProductoDTO;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepo;


    public List<Producto> getProductos() {
        return productoRepo.findAll();
    }


    public Producto createProducto(ProductoDTO productoDTO) {
        if (productoRepo.existsByNombre(productoDTO.getNombre())) {
            throw new IllegalArgumentException("El producto ya existe");
        }

        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(productoDTO.getNombre());
        nuevoProducto.setCosto(productoDTO.getPrecio());
        nuevoProducto.setCantidadDisponible(productoDTO.getCantidadDisponible());
        nuevoProducto.setMarca(productoDTO.getMarca());

        return productoRepo.save(nuevoProducto);
    }

    public Producto findProducto(Long id) {
        return productoRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }


    public void deleteProducto(Long id) {
        Producto producto = findProducto(id);
        if(producto != null){
            productoRepo.delete(producto);
        }

    }

    public List<Producto> productosConBajoStock() {
        return productoRepo.findByCantidadDisponibleLessThanEqual(1000);
    }


}
