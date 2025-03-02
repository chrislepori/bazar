package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.ProductoDTO;
import com.proyectofinal.bazar.dto.ProductoResponseDTO;
import com.proyectofinal.bazar.model.Producto;
import com.proyectofinal.bazar.service.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        Producto nuevoProducto = productoService.createProducto(productoDTO);

        // Convertimos el Producto en un ProductoResponseDTO antes de devolverlo
        ProductoResponseDTO responseDTO = new ProductoResponseDTO(
                nuevoProducto.getId(),
                nuevoProducto.getNombre(),
                nuevoProducto.getCosto(),
                nuevoProducto.getCantidadDisponible(),
                nuevoProducto.getMarca()

        );

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> encontrarProducto(@PathVariable Long id) {
        Producto productoEncontrado = productoService.findProducto(id);

        ProductoResponseDTO responseDTO = new ProductoResponseDTO(
                productoEncontrado.getId(),
                productoEncontrado.getNombre(),
                productoEncontrado.getCosto(),
                productoEncontrado.getCantidadDisponible(),
                productoEncontrado.getMarca()
        );

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/bajo-stock")
    public ResponseEntity<List<ProductoResponseDTO>> productosConBajoStock(){
        List<Producto> productosBajoStock = productoService.productosConBajoStock();
        List<ProductoResponseDTO> responseDTOs = new ArrayList<>();
        for(Producto p: productosBajoStock){
            ProductoResponseDTO productoResponseDTO = new ProductoResponseDTO(p.getId(), p.getNombre(), p.getCosto(), p.getCantidadDisponible(), p.getMarca());
            responseDTOs.add(productoResponseDTO);
        }
        return productosBajoStock.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
                : ResponseEntity.ok(responseDTOs);

    }

    @GetMapping("/all-products")
    public ResponseEntity<List<ProductoResponseDTO>> getProducts(){
        List<Producto> products = productoService.getProductos();
        List<ProductoResponseDTO> responseDTOs = new ArrayList<>();
        for(Producto p: products){
            ProductoResponseDTO productoResponseDTO = new ProductoResponseDTO(p.getId(), p.getNombre(), p.getCosto(), p.getCantidadDisponible(), p.getMarca());
            responseDTOs.add(productoResponseDTO);
        }
        return products.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList()) : ResponseEntity.ok().body(responseDTOs);


    }



}






