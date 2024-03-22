package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private IClienteRepository clienteRepo;

    @Override
    public List<Cliente> getClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public void createCliente(Cliente cliente) {
        clienteRepo.save(cliente);

    }

    @Override
    public Cliente findCliente(Long idCliente) {
        return clienteRepo.findById(idCliente).orElse(null);
    }

    @Override
    public void deleteCliente(Long idCliente) {
        clienteRepo.deleteById(idCliente);

    }

    @Override
    public void editCliente(Long idClienteOriginal, Long idCliente, String nombre, String apellido, String dni) {
        Cliente cli = this.findCliente(idClienteOriginal);
        if (cli != null) {
            cli.setIdCliente(idCliente);
            cli.setNombre(nombre);
            cli.setApellido(apellido);
            cli.setDni(dni);
            this.createCliente(cli);
        }

    }
}
