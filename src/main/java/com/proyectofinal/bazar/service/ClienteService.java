package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.dto.ClienteDTO;
import com.proyectofinal.bazar.dto.ClienteResponseDTO;
import com.proyectofinal.bazar.exception.ApiException;
import com.proyectofinal.bazar.exception.MensajeError;
import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepo;
    private final ModelMapper modelMapper;


    public Page<Cliente> getClientesPagination(int numeroPagina, int cantidad ) {
        Pageable pageable = PageRequest.of(numeroPagina, cantidad);
        return clienteRepo.findAll(pageable);
    }


    public ClienteResponseDTO createCliente(ClienteDTO clienteDTO) {
        if (clienteRepo.findByDni(clienteDTO.getDni()).isPresent()) {
            throw new ApiException(MensajeError.CLIENTE_EXISTING);
        }
        Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        clienteRepo.save(cliente);
        return modelMapper.map(cliente, ClienteResponseDTO.class);

    }


    public ClienteResponseDTO findCliente(String dni) {
         Cliente cliente = clienteRepo.findByDni(dni)
                .orElseThrow(() -> new ApiException(MensajeError.CLIENTE_NOT_FOUND));
         return modelMapper.map(cliente, ClienteResponseDTO.class);
    }


    public void deleteCliente(String dni) {
        Cliente cliente = clienteRepo.findByDni(dni)
                .orElseThrow(() -> new ApiException(MensajeError.CLIENTE_NOT_FOUND));
        clienteRepo.delete(cliente);

    }


}
