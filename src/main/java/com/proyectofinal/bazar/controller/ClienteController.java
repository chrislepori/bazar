package com.proyectofinal.bazar.controller;

import com.proyectofinal.bazar.model.Cliente;
import com.proyectofinal.bazar.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {
    @Autowired
    private IClienteService clienteServ;

    @GetMapping("/clientes/get")
    public List<Cliente> getClientes() {
        return clienteServ.getClientes();
    }

    @PostMapping("cliente/create")
    public void createCliente(@RequestBody Cliente cli) {
        clienteServ.createCliente(cli);

    }

    @GetMapping("cliente/find/{idCliente}")
    public Cliente findCliente(@PathVariable Long idCliente) {
        return clienteServ.findCliente(idCliente);
    }

    @DeleteMapping("cliente/delete/{idCliente}")
    public void deleteCliente(@PathVariable Long idCliente) {
        clienteServ.deleteCliente(idCliente);
    }

    @PutMapping("/cliente/edit/{idClienteOriginal}")
    public void editCliente(@PathVariable Long idClienteOriginal,
                            @RequestParam(required = false) Long idCliente,
                            @RequestParam(required = false) String nombre,
                            @RequestParam(required = false) String apellido,
                            @RequestParam(required = false) String dni) {
        clienteServ.editCliente(idClienteOriginal, idCliente, nombre, apellido, dni);
    }


}
