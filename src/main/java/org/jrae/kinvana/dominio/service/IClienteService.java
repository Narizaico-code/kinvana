package org.jrae.kinvana.dominio.service;

import org.jrae.kinvana.persistence.entity.Cliente;

import java.util.List;

public interface IClienteService {
    // Firmas de metodos del CRUD
    List<Cliente> listarClientes();
    Cliente buscarClientePorId(Integer codigo);
    void guardarCliente(Cliente cliente);
    void eliminarCliente(Cliente cliente);
}
