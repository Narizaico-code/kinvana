package org.jrae.kinvana.dominio.service;

import org.jrae.kinvana.persistence.crud.ClienteCrud;
import org.jrae.kinvana.persistence.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClienteService implements IClienteService{

    // Inyeccion de dependencias de mi Repositorio (ClienteCrud) [ClienteRepository]
    @Autowired
    private ClienteCrud crud;
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = crud.findAll();
        return clientes;
    }

    @Override
    public Cliente buscarClientePorId(Integer codigo) {
        Cliente cliente = crud.findById(codigo).orElse(null);
        return cliente;
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        crud.save(cliente); // Para agregar nuevo y editar
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        crud.delete(cliente);
    }
}
