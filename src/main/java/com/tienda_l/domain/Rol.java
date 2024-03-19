package com.tienda_l.domain;
//Esta clase va a mapear una base de datos

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;
    
    private String nombre;
    
    @Column(name="id_usuario")
    private Long idUsuario;
}