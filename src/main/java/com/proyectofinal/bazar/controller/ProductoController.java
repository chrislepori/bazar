package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.ProductoDTO;
import com.proyectofinal.bazar.dto.ProductoPaginationDTO;
import com.proyectofinal.bazar.dto.ProductoResponseDTO;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.service.ProductoService;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;


    @PostMapping
    public ResponseEntity<ProductoResponseDTO> createProducto(@RequestBody ProductoDTO productoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.createProducto(productoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> encontrarProducto(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.findProducto(id));
    }

    @GetMapping("/bajo-stock")
    public ResponseEntity<List<ProductoResponseDTO>> productosConBajoStock() {
        return ResponseEntity.ok().body(productoService.productosConBajoStock());
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductoResponseDTO>> getProducts() {
        return ResponseEntity.ok().body(productoService.getProductos());
    }

    @GetMapping("/productsPagination")
    public ResponseEntity<Page<Producto>> getProductosPagination(ProductoPaginationDTO productoPaginationDTO) {
        Page<Producto> pageProducts = productoService.getProductsPagination(productoPaginationDTO.getNumeroPagina(), productoPaginationDTO.getCantidadElementos());
        return ResponseEntity.ok().body(pageProducts);
    }


}






