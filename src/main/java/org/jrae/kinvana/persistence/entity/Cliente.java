package org.jrae.kinvana.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Clientes")
// Lombok
@Data //Genera los getters and setters
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode // Codigo que se genera para la entidad cuando el programa se inicia(Codigo de autentificacion de la entidad)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoCliente; //Permite usar null en vez de 0
    @Column
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String genero; // Es un enum
    private Integer edad;
}
