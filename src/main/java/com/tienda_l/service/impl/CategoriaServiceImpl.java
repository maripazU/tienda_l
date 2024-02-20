package com.tienda_l.service.impl;

import com.tienda_l.dao.CategoriaDao;
import com.tienda_l.domain.Categoria;
import com.tienda_l.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //servicio disponible en el servidor web
public class CategoriaServiceImpl implements CategoriaService {

    //no vamos a tener que inicializar y no va a haber una copia, todos los usuarios van a usar un mismo objeto, reduce la cantidad de memoria 
    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly=true) //hace que la función sea solo para leer 
    public List<Categoria> getCategorias(boolean activos) { //categoriaDao hereda de jpRepository
        //si solo quiero los activos: --- esto realiza una operación transaccional en la base de datos, por ende el método es transaccional --- por eso el @Transactional
        var lista=categoriaDao.findAll(); //definimos un arraylist con el nombre de lista
        if (activos) {
            lista.removeIf(c -> !c.isActivo()); //remueva de la lista todo elemento no activo
                           //predicado: expresiones landa. = para cada elemento c de tipo categoria  
        }
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null); //si lo encuentra devuelve la categoria, si no entonces devuelve null
    }

    @Override
    @Transactional() //estos no tienen el readOnly=true pq sí van a modificar
    public void deleteCategoria(Categoria categoria) {
       categoriaDao.delete(categoria);
    }

    @Override
    @Transactional() //estos no tienen el readOnly=true pq sí van a modificar
    public void saveCategoria(Categoria categoria) {
        categoriaDao.save(categoria);
    }

}
