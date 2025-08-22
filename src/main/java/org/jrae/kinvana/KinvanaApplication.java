package org.jrae.kinvana;

import org.jrae.kinvana.dominio.service.IClienteService;
import org.jrae.kinvana.persistence.entity.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.jrae.kinvana.persistence.entity.Cliente;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class KinvanaApplication implements CommandLineRunner {

	// Inyeccion de dependencia
	@Autowired
	private IClienteService clienteService;
	// Crear nuestro Objeto (herramienta) Logger para interactuar con la consola
	private static final Logger logger = LoggerFactory.getLogger(KinvanaApplication.class);
	// Crear un objeto String para saltos de linea que no los maneja el logger
	String sl = System.lineSeparator(); // Salto de linea

	public static void main(String[] args) {
		logger.info("AQUI INICIA NUESTRA APLICACION");
		SpringApplication.run(KinvanaApplication.class, args);
		logger.info("AQUI TERMINO LA APLICACION");
	}

	@Override
	public void run(String... args) throws Exception {
		kinvanaClienteApp();
	}

	private void kinvanaClienteApp(){
		logger.info("+++++++++++++ APLICACION DE REGISTRO DE CLIENTES +++++++++++++");
		var salir = false;
		var consola = new Scanner(System.in);
		while (!salir){
			var opcion = mostrarMenu(consola);
			salir = ejecutarOpciones(consola, opcion);
			logger.info(sl);
		}
	}

	private int mostrarMenu(Scanner consola){
		logger.info("""
				\n *** Aplicacion ***
				1. Listar todos los clientes.
				2. Buscar cliente por codigo.
				3. Agregar nuevo cliente.
				4. Modificar cliente.
				5. Eliminar cliente.
				6. Salir.
				Elije una opcion: \s""");
		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		var salir = false;
		switch (opcion) {
			case 1 -> {
				logger.info(sl+"*** Listado de todos los clientes ***"+sl);
				List<Cliente> clientes = clienteService.listarClientes();
				clientes.forEach(c -> logger.info(c.toString()+sl));
			}
			case 2 -> {
				logger.info(sl+"*** Buscar cliente por su codigo ***"+sl);
				var codigo = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteService.buscarClientePorId(codigo);
				if (cliente != null) {
					logger.info("Cliente encontrado: " + sl + cliente + sl);
				}else{
					logger.info("Cliente no encontrado: " + sl + cliente + sl);
				}
			}
			case 3 -> {
				logger.info(sl+"*** Agregar nuevo cliente ***" + sl);
				logger.info("Ingrese el nombre: ");
				var nombre = consola.nextLine();
				logger.info("Ingrese el apellido: ");
				var apellido = consola.nextLine();
				logger.info("Ingrese el numero de telefono: ");
				var telefono = consola.nextLine();
				logger.info("Ingrese el correo electronico: ");
				var correo = consola.nextLine();
				logger.info("Ingrese el genero (masculino, femenino o no): ");
				var genero = consola.nextLine();
				logger.info("Ingrese la edad: ");
				var edad = Integer.parseInt(consola.nextLine());
				var cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setTelefono(telefono);
				cliente.setCorreo(correo);
				if (genero.equalsIgnoreCase("masculino") || genero.equalsIgnoreCase("femenino") || genero.equalsIgnoreCase("no")){
					cliente.setGenero(genero);
				}else{
					logger.info("Debe elegir masculino, femenino o no");
				}
				cliente.setEdad(edad);
				clienteService.guardarCliente(cliente);
				logger.info("Cliente agregado: " + sl + cliente + sl);
			}
			case 4 -> {
				logger.info(sl + "*** Modificar Cliente ***" + sl);
				logger.info("Ingrese el codigo del Cliente a editar");
				var codigo = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteService.buscarClientePorId(codigo);
				if (cliente != null){
					logger.info("Ingrese el nombre: ");
					var nombre = consola.nextLine();
					logger.info("Ingrese el apellido: ");
					var apellido = consola.nextLine();
					logger.info("Ingrese el numero de telefono: ");
					var telefono = consola.nextLine();
					logger.info("Ingrese el correo electronico: ");
					var correo = consola.nextLine();
					logger.info("Ingrese el genero (masculino, femenino o no): ");
					var genero = consola.nextLine();
					logger.info("Ingrese la edad: ");
					var edad = Integer.parseInt(consola.nextLine());
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setTelefono(telefono);
					cliente.setCorreo(correo);
					if (genero.equalsIgnoreCase("masculino") || genero.equalsIgnoreCase("femenino") || genero.equalsIgnoreCase("no")){
						cliente.setGenero(genero);
					}else{
						logger.info("Debe elegir masculino, femenino o no");
					}
					cliente.setEdad(edad);
					clienteService.guardarCliente(cliente);
					logger.info("Cliente modificado: " + sl + cliente + sl);
				}else {
					logger.info("Cliente no encontrado: " + sl + cliente + sl);
				}
			}
			case 5 -> {
				logger.info(sl+"*** Eliminar cliente ***"+sl);
				logger.info("Ingrese el codigo del cliente a eliminar");
				var codigo = Integer.parseInt(consola.nextLine());
				var cliente = clienteService.buscarClientePorId(codigo);
				if (cliente != null){
					clienteService.eliminarCliente(cliente);
					logger.info("Cliente eliminado, adios: " + sl + cliente + sl);
				}else{
					logger.info("Cliente no encontrado: " + sl + cliente + sl);
				}
			}
			case 6 -> {
				logger.info("Hasta pronto, Vaquero!☺☻♥♦♣♠•◘○♂▬!,7B" + sl + sl);
				salir = true;
			}
			default -> logger.info("Opcion no valida!!!");
		}
		return salir;
	}
}