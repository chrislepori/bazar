package com.proyectofinal.bazar.service;

import com.proyectofinal.bazar.model.Cliente;
import java.util.List;

public interface IClienteService {
    public List<Cliente> getClientes();

    public void createCliente(Cliente cliente);

    public Cliente findCliente(Long idCliente);

    public void deleteCliente(Long idCliente);

    public void editCliente(Long idClienteOriginal, Long idCliente, String nombre, String apellido, String dni);
}
