package com.tienda_l.controller;

import com.tienda_l.domain.Item;
import com.tienda_l.domain.Producto;
import com.tienda_l.service.ItemService;
import com.tienda_l.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductoService productoService;

    //este mapping se realiza desde el ícono del carrito
    @GetMapping("/carrito/agregar/{idProducto}")
    public ModelAndView agregarItem(Model model, Item item) {
        Item item2 = itemService.get(item); //ir a ver si ya el producto se ha agregado al carrito (si ya está hay que actualizar la cantidad y si no hay que agregarlo de cero)
        if (item2 == null) { //no existe el producto en el carrito
            Producto p = productoService.getProducto(item);
            item2 = new Item(p); //creando un nuevo item a partir de la info del producto.
        }
        itemService.save(item2); //se guarda en el arrayList
        var lista = itemService.gets(); //con esto se obtine la lista de todos los items
        var totalCarrito = 0;
        var carritoTotalVenta = 0;

        //para saber la cantidad de items
        for (Item i : lista) {
            totalCarrito += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarrito);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        
        //modelandview estructura para actualizar solo un pedazo de la página (ajax??) solamente una zona de la página
        return new ModelAndView("/carrito/fragmentos :: verCarrito"); //que se actualice ese fragmento
    }
    
    @GetMapping("/carrito/listado")
    public String listado(Model model) {
        var items = itemService.gets();
        model.addAttribute("items", items);
        
        //para saber cuánto hay que pagar por la compra
        var carritoTotalVenta=0;
        for (Item i : items) {
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listaTotal", carritoTotalVenta); 
        return "/carrito/listado";
    }

}
