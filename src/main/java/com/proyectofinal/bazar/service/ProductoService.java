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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepo;
    private final ModelMapper modelMapper;


    public List<ProductoResponseDTO> getProductos() {
        List<Producto> productos = productoRepo.findAll();
        return productos.stream()
                .map(producto -> modelMapper.map(producto, ProductoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public Page<Producto> getProductsPagination(int numeroPagina, int cantidad) {
        Pageable pageable = PageRequest.of(numeroPagina, cantidad);
        return productoRepo.findAll(pageable);

    }


    public ProductoResponseDTO createProducto(ProductoDTO productoDTO) {
        if (productoRepo.existsByNombre(productoDTO.getNombre())) {
            throw new ApiException(MensajeError.PRODUCT_EXISTING);
        }

        Producto producto = modelMapper.map(productoDTO, Producto.class);

        productoRepo.save(producto);

        return modelMapper.map(producto, ProductoResponseDTO.class);
    }

    public ProductoResponseDTO findProducto(Long id) {
        Producto producto = productoRepo.findById(id)
                .orElseThrow(() -> new ApiException(MensajeError.PRODUCTO_NOT_FOUD));
        return modelMapper.map(producto, ProductoResponseDTO.class);
    }


    public void deleteProducto(Long id) {
        Producto producto = productoRepo.findById(id)
                .orElseThrow(() -> new ApiException(MensajeError.PRODUCTO_NOT_FOUD));
        productoRepo.delete(producto);

    }

    public List<ProductoResponseDTO> productosConBajoStock() {
        List<Producto> productosBajoStock = productoRepo.findByCantidadDisponibleLessThanEqual(1000);
        return productosBajoStock.stream()
                .map(producto -> modelMapper.map(producto, ProductoResponseDTO.class))
                .collect(Collectors.toList());

    }


}
