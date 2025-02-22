package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.VentaDTO;
import com.proyectofinal.bazar.dto.VentaResponseDTO;
import com.proyectofinal.bazar.model.Venta;
import com.proyectofinal.bazar.service.VentaService;
import lombok.AllArgsConstructor;
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
    public ResponseEntity<VentaResponseDTO> crearVenta(@RequestBody VentaDTO ventaDTO) {
        Venta venta = ventaService.createVenta(ventaDTO);
        VentaResponseDTO ventaResponseDTO = new VentaResponseDTO(venta.getId(), venta.getFechaVenta(), venta.getTotal(), venta.getCliente());
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaResponseDTO);

    }

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> ventasPorDia(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<Venta> ventas = ventaService.ventasPorDia(fecha);
        List<VentaResponseDTO> responseDTOS = new ArrayList<>();
        for (Venta venta : ventas) {
            VentaResponseDTO ventaResponseDTO = new VentaResponseDTO(venta.getId(), venta.getFechaVenta(), venta.getTotal(), venta.getCliente());
            responseDTOS.add(ventaResponseDTO);
        }
        return ventas.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
                : ResponseEntity.ok(responseDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();

    }





}
