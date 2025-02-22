package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ClienteDTO;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepo;


    public List<Cliente> getClientes() {
        return clienteRepo.findAll();
    }


    public Cliente createCliente(ClienteDTO clienteDTO) {
        if (clienteRepo.findByDni(clienteDTO.getDni()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un cliente con ese DNI.");
        }
        Cliente cliente = new Cliente();
        cliente.setDni(clienteDTO.getDni());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setDomicilio(clienteDTO.getDomicilio());

        return clienteRepo.save(cliente);
    }


    public Cliente findCliente(String dni) {
        return clienteRepo.findByDni(dni)
                .orElseThrow(()-> new IllegalArgumentException("No se encontro el cliente"));
    }


    public void deleteCliente(String dni) {
        Cliente cliente = clienteRepo.findByDni(dni)
                .orElseThrow(()-> new IllegalArgumentException("No se encontro el cliente"));
        clienteRepo.delete(cliente);

    }



}
