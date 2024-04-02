package com.tienda_l.service.impl;

import com.tienda_l.domain.Item;
import com.tienda_l.service.ItemService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> gets() {
        return listaItems;
    }

    //Busca en el arrayList y si lo encuentra lo retorna, si no lo encuentra retorna null
    @Override
    public Item get(Item item) {
        for (Item i : listaItems) { //se va a recorrer la lista de items y si s encuentra el item con el mismo producto se devuelve y si no se devuelve null
            if (i.getIdProducto() == item.getIdProducto()) {
                return i;
            }
        }
        return null;
    }

    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) { 
            posicion++;
            if (i.getIdProducto() == item.getIdProducto()) {
                existe = true;
                break;
            }
        }
        
        if (existe) {
            listaItems.remove(posicion);
        }
    }

    @Override
    public void save(Item item) {
        var existe = false;
        //se busca si el item ya existe en el carrito
        for (Item i : listaItems) {
            if (i.getIdProducto() == item.getIdProducto()) {
                existe = true;
                i.setCantidad(i.getCantidad()+1);
                break;
            }
        }
        
        if (!existe) {
            item.setCantidad(1);
            listaItems.add(item);
        }
    }

    @Override
    public void update(Item item) {
        for (Item i : listaItems) {
            if (i.getIdProducto() == item.getIdProducto()) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }

    @Override
    public void facturar() {

    }

}
