package com.tienda_l.domain;
//Esta clase va a mapear una base de datos

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

//con @Data ya estan los get y los set
@Data
//con @Entity se dice que vamos a usar una tabla de la base de datos
@Entity
//con @Table vamos a decirle que tabla vamos a usar
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    //@Id se refiere a que el siguiente atributo es el primary key (en este caso idProducto)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;
    //private Long idCategoria
    private String descripcion;
    private String detalle;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;
    
    //Anotaciones
    @ManyToOne
    @JoinColumn(name="id_categoria")
    private Categoria categoria;
}