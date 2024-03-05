package com.tienda_l.service;

import com.tienda_l.domain.Producto;
import java.util.List;

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
    
}
