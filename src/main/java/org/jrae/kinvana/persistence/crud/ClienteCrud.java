package org.jrae.kinvana.persistence.crud;

import org.jrae.kinvana.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;

public interface ClienteCrud extends JpaRepository<Cliente, Integer> {
    // Puede sustituir al DAO
    // Está interfaz tiene todos los metodos genericos del CRUD
    //
}
