package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.dto.ClienteDTO;
import com.proyectofinal.bazar.dto.ClienteResponseDTO;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ClienteResponseDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.createCliente(clienteDTO);
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente.getNombre(), cliente.getApellido(), cliente.getDni(), cliente.getDomicilio());
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponseDTO);

    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String dni){
        clienteService.deleteCliente(dni);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{dni}")
    public ResponseEntity<ClienteResponseDTO> encontrarCliente(@PathVariable String dni){
        Cliente cliente = clienteService.findCliente(dni);
        ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente.getNombre(), cliente.getApellido(), cliente.getDni(), cliente.getDomicilio());
        return ResponseEntity.ok(clienteResponseDTO);


    }

    @GetMapping("/traer-clientes")
    public ResponseEntity<List<ClienteResponseDTO>> mostrarClientes(){
        List<Cliente> clientes = clienteService.getClientes();
        List<ClienteResponseDTO> clientesResponseDTOs = new ArrayList<>();
        for(Cliente cliente: clientes){
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(cliente.getNombre(), cliente.getApellido(), cliente.getDni(), cliente.getDomicilio());
            clientesResponseDTOs.add(clienteResponseDTO);
        }
        return clientes.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
                : ResponseEntity.ok(clientesResponseDTOs);

    }











}




