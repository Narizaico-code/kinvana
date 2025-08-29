package org.jrae.kinvana.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.jrae.kinvana.dominio.service.IClienteService;
import org.jrae.kinvana.persistence.entity.Cliente;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ViewScoped
public class IndexController {

    @Autowired
    IClienteService clienteService;
    private List<Cliente> clientes;
    private Cliente clienteSeleccionado;
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    String sl = System.lineSeparator();

    @PostConstruct
    public void init(){
        cargarDatos();
    }

    public void cargarDatos(){
        this.clientes = this.clienteService.listarClientes();
        this.clientes.forEach(cliente -> logger.info(cliente.toString() + sl));
    }
    public void agregarCliente(){
        this.clienteSeleccionado = new Cliente();
    }
    public void guardarCliente(){
        logger.info("Cliente a guardar: " + this.clienteSeleccionado + sl);
        // Agregar(insert)
        if (this.clienteSeleccionado.getCodigoCliente() == null){
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            this.clientes.add(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null ,
                    new FacesMessage("Cliente agregado"));

        }
        // Modificar (update)
        else {
            this.clienteService.guardarCliente(this.clienteSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente Actualizado"));
        }

        // Ocultar la ventana Modal
        PrimeFaces.current().executeScript("PF('ventanaModalCliente').hide()");
        // Actualizar la tabla con un metodo AJAX
        PrimeFaces.current().ajax().update(
                "formulario-clientes:mensaje_emergente",
                "formulario-clientes:tabla-clientes");
        // Reset del cliente Seleccionado
        this.clienteSeleccionado = null;
    }
    public void eliminarCliente(){
        // Mostrar en consola
        logger.info("Cliente a eliminar: " + this.clienteSeleccionado + sl);
        // Llamar a nuestro servicio de eliminacion de cliente
        this.clienteService.eliminarCliente(clienteSeleccionado);
        // Eliminarlo de la lista clientes
        this.clientes.remove(clienteSeleccionado);
        // Limpiar nuestro clienteSeleccionado
        this.clienteSeleccionado = null;
        // Enviar un mensaje emergente de confirmacion
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cliente eliminado"));
        // Actualizar la tabla con ajax
        PrimeFaces.current().ajax().update(
                "formulario-clientes:mensaje_emergente",
                "formulario-clientes:tabla-clientes");
    }

}
