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
			case 3 -> {}
			case 4 -> {}
			case 5 -> {

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