package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.ProductoResponseDTO;
import com.proyectofinal.bazar.dto.VentaDTO;
import com.proyectofinal.bazar.dto.VentaPaginationDTO;
import com.proyectofinal.bazar.dto.VentaResponseDTO;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.service.VentaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/ventas")
@AllArgsConstructor

public class VentaController {

    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaResponseDTO> crearVenta(@RequestBody @Valid VentaDTO ventaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.createVenta(ventaDTO));
    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> ventasPorDia(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok().body(ventaService.ventasPorDia(fecha));
    }


    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> encontrarVenta(@PathVariable Long id){
        return ResponseEntity.ok().body(ventaService.findVenta(id));
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<List<ProductoResponseDTO>> productosDeUnaVenta(@PathVariable Long id){
        return ResponseEntity.ok().body(ventaService.productosDeUnaVenta(id));
    }

    @GetMapping("/ventasPagination")
    public ResponseEntity<Page<Venta>> getVentasPagination(VentaPaginationDTO ventaPaginationDTO){
        return ResponseEntity.ok().body(ventaService.getVentasPagination(ventaPaginationDTO.getNumeroPagina(), ventaPaginationDTO.getCantidadElementos()));
    }

    @GetMapping("/ventaMayor")
    public ResponseEntity<VentaResponseDTO> mayorVenta(){
        return ResponseEntity.ok().body(ventaService.obtenerVentaMayor());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }






}
