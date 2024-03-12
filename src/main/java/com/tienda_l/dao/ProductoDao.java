package com.tienda_l.dao;

import com.tienda_l.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoDao extends JpaRepository <Producto, Long>{ //me permite consultar, extraer, agregar, editar registros 
    
    //Esta consulta ampliada utiliza métodos @Query
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup); //busca un número entre esos números y la ordenar alfabéticamente según su descripción
        
    //Esta consulta utiliza lenguaje JPQL
    //Producto se escribe con mayúscula porque hace referencia a la clase de java
    @Query(value = "SELECT p FROM Producto p WHERE p.precio BETWEEN :precioInf and :precioSup ORDER BY p.descripcion ASC")
    public List<Producto> metodoJPQL(@Param("precioInf") double percioInf, @Param("precioSup") double precioSub);
    
    //Esta consulta utiliza lenguaje SQL nativo
    @Query(nativeQuery = true, value ="SELECT * FROM producto p WHERE p.precio BETWEEN :precioInf and :precioSup ORDER BY p.descripcion ASC")
    public List<Producto> metodoNativo(@Param("precioInf") double percioInf, @Param("precioSup") double precioSub);
    
}
