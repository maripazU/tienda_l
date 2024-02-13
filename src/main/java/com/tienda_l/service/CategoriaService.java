package com.tienda_l.service;

import com.tienda_l.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    //Se obtiene un arraylist de objetos tipo Categora 
    public List<Categoria> getCategorias(boolean activos);
}
