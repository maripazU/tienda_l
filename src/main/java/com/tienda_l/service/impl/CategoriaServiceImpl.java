package com.tienda_l.service.impl;

import com.tienda_l.dao.CategoriaDao;
import com.tienda_l.domain.Categoria;
import com.tienda_l.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //servicio disponible en el servidor web
public class CategoriaServiceImpl implements CategoriaService{

    //no vamos a tener que inicializar y no va a haber una copia, todos los usuarios van a usar un mismo objeto, reduce la cantidad de memoria 
    @Autowired
    private CategoriaDao categoriaDao;
    
    @Override
    public List<Categoria> getCategorias(boolean activos) { //categoriaDao hereda de jpRepository
        return categoriaDao.findAll(); //devuelve una lista de categorias. Array list con las categorias de la tabla. 
    }
    
}
