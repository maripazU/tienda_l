package com.tienda_l.dao;

import com.tienda_l.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository <Producto, Long>{ //me permite consultar, extraer, agregar, editar registros 
    
}
