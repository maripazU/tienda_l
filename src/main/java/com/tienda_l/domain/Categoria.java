package com.tienda_l.domain;
//Esta clase va a mapear una tabla de una base de datos 

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;


@Data //esto va a crear todos los métodos set y get sin que estén programados ahí ni estén definidos 
@Entity //le decimos a java que esta clase es una entidad (vamos a usar una tabla de la base de datos)
@Table(name="categoria") //aquí le estamos diciendo cuál tabla de la base de datos 
public class Categoria implements Serializable{ //quiere decir que se va a mandar a traer información de algún lugar extern (desde la máquina local a algún otro lugar)
    private static final long serialVersionUID=1L; //si no hay ningún registro comience a enumerar con el 1
    
    @Id //esto significa que este es el campo clave e irrepetible (primaryKey)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //recupere la información que se va generando en la base de datos 
    @Column(name="id_categoria") //en la tabla se llama así pero nosotros lo vamos a tratar como idCategoria
    private long idCategoria;
    private String descripcion;
    private String rutaImagen;
    private boolean activo;
}
