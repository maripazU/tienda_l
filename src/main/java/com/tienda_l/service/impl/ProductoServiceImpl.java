package com.tienda_l.service.impl;

import com.tienda_l.dao.ProductoDao;
import com.tienda_l.domain.Producto;
import com.tienda_l.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //servicio disponible en el servidor web
public class ProductoServiceImpl implements ProductoService {

    //no vamos a tener que inicializar y no va a haber una copia, todos los usuarios van a usar un mismo objeto, reduce la cantidad de memoria 
    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly=true) //hace que la función sea solo para leer 
    public List<Producto> getProductos(boolean activos) { //productoDao hereda de jpRepository
        //si solo quiero los activos: --- esto realiza una operación transaccional en la base de datos, por ende el método es transaccional --- por eso el @Transactional
        var lista=productoDao.findAll(); //definimos un arraylist con el nombre de lista
        if (activos) {
            lista.removeIf(c -> !c.isActivo()); //remueva de la lista todo elemento no activo
                           //predicado: expresiones landa. = para cada elemento c de tipo producto  
        }
        return lista;
    }

    @Override
    @Transactional(readOnly=true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null); //si lo encuentra devuelve la producto, si no entonces devuelve null
    }

    @Override
    @Transactional() //estos no tienen el readOnly=true pq sí van a modificar
    public void deleteProducto(Producto producto) {
       productoDao.delete(producto);
    }

    @Override
    @Transactional() //estos no tienen el readOnly=true pq sí van a modificar
    public void saveProducto(Producto producto) {
        productoDao.save(producto);
    }
    
    //Esta consulta utiliza el método @Query
    @Override
    @Transactional(readOnly=true)
    public List<Producto> metodoQuery(double precioInf, double precioSup){
        return productoDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
    }
    
    //Esta consulta utiliza el método JPQL
    @Override
    @Transactional(readOnly=true)
    public List<Producto> metodoJPQL(@Param("precioInf") double percioInf, @Param("precioSup") double precioSub){
        return productoDao.metodoJPQL(percioInf, precioSub);
    }
    
    //Esta consulta utiliza el método lenguaje SQL
    @Override
    @Transactional(readOnly=true)
    public List<Producto> metodoNativo(@Param("precioInf") double percioInf, @Param("precioSup") double precioSub){
        return productoDao.metodoNativo(percioInf, precioSub);
    }

}
