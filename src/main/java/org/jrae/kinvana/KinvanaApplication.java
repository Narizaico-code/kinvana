package org.jrae.kinvana;

import org.jrae.kinvana.dominio.service.IClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class KinvanaApplication implements CommandLineRunner {

	// Inyeccion de dependencia
	//@Autowired
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
		return false;
	}
}