package com.tienda_l.dao;

import com.tienda_l.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDao extends JpaRepository <Categoria, Long>{ //me permite consultar, extraer, agregar, editar registros 
    
}
