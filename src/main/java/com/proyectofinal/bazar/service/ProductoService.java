package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ProductoDTO;
import com.proyectofinal.bazar.dto.ProductoResponseDTO;
import com.proyectofinal.bazar.exception.ApiException;
import com.proyectofinal.bazar.exception.MensajeError;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.repository.ProductoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepo;
    private final ModelMapper modelMapper;


    public List<Producto> getProductos() {
        return productoRepo.findAll();
    }

    public Page<Producto> getProductsPagination(int numeroPagina, int cantidad){
        Pageable pageable = PageRequest.of(numeroPagina, cantidad);
        return productoRepo.findAll(pageable);

    }


    public ProductoResponseDTO createProducto(ProductoDTO productoDTO) {
        if (productoRepo.existsByNombre(productoDTO.getNombre())) {
            throw new ApiException(MensajeError.PRODUCTO_NOT_FOUD);
        }


        //usando modelMapper
        Producto producto = modelMapper.map(productoDTO, Producto.class);

        productoRepo.save(producto);
        return modelMapper.map(producto, ProductoResponseDTO.class);
    }

    public Producto findProducto(Long id) {
        return productoRepo.findById(id)
                .orElseThrow(() -> new ApiException(MensajeError.PRODUCT_EXISTING));
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
