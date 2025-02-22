package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.service.VentaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventas")
@AllArgsConstructor

public class VentaController {

    private final VentaService ventaService;




}
