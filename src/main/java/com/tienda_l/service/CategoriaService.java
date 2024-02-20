package com.tienda_l.service;

import com.tienda_l.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    //Se obtiene un arraylist de objetos tipo Categora 
    public List<Categoria> getCategorias(boolean activos);
    
    //se obtiene un objeto categoria a partir del idCategoria que nos pasan
    public Categoria getCategoria(Categoria categoria);
    //si tiene un valor en la base de datos el bd devuelve todo el objeto con todos los datos. 
    
    //se elimina un registro de la tabla categoria a partir del idCategoria que nos pasan
    public void deleteCategoria(Categoria categoria);
    
    //se hace lo siguiente: 
    //si idCategoria tiene un valor, se actualiza
    //si idcategoria no tiene un valor, se inserta
    public void saveCategoria(Categoria categoria);
    
}
