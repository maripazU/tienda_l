package com.tienda_l.domain;
//Esta clase va a mapear una base de datos

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String rutaImagen;
    private boolean activo;
    
    @OneToMany
    @JoinColumn(name="id_usuario")
    private List<Rol> roles;
    //para ver los roles que tiene un usuario
}