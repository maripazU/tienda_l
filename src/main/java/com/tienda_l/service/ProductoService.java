package com.tienda_l.service;

import com.tienda_l.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoService {
    //Se obtiene un arraylist de objetos tipo Categora 
    public List<Producto> getProductos(boolean activos);
    
    //se obtiene un objeto producto a partir del idProducto que nos pasan
    public Producto getProducto(Producto producto);
    //si tiene un valor en la base de datos el bd devuelve todo el objeto con todos los datos. 
    
    //se elimina un registro de la tabla producto a partir del idProducto que nos pasan
    public void deleteProducto(Producto producto);
    
    //se hace lo siguiente: 
    //si idProducto tiene un valor, se actualiza
    //si idproducto no tiene un valor, se inserta
    public void saveProducto(Producto producto);
    
    //Esta consulta utiliza el método @Query
    public List<Producto> metodoQuery(double precioInf, double precioSup);
    
    //Esta consulta utiliza el método JPQL
    public List<Producto> metodoJPQL(@Param("precioInf") double percioInf, @Param("precioSup") double precioSub);
    
    //Esta consulta utiliza el método lenguaje SQL
    public List<Producto> metodoNativo(@Param("precioInf") double percioInf, @Param("precioSup") double precioSub);
    
}
