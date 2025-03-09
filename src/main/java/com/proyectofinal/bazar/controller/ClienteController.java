package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.ClienteDTO;
import com.proyectofinal.bazar.dto.ClientePaginationDTO;
import com.proyectofinal.bazar.dto.ClienteResponseDTO;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.service.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCliente(@RequestBody @Valid ClienteDTO clienteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.createCliente(clienteDTO));
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String dni) {
        clienteService.deleteCliente(dni);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{dni}")
    public ResponseEntity<ClienteResponseDTO> encontrarCliente(@PathVariable String dni) {
        return ResponseEntity.ok().body(clienteService.findCliente(dni));
    }

    @GetMapping("/all-clientesPagination")
    public ResponseEntity<Page<Cliente>> getClientesPagination(ClientePaginationDTO clientePaginationDTO) {
        return ResponseEntity.ok().body(clienteService.getClientesPagination(clientePaginationDTO.getNumeroPagina(), clientePaginationDTO.getCantidadElementos()));



    }


}




