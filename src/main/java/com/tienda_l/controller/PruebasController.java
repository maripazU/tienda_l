package com.tienda_l.controller;

import com.tienda_l.service.CategoriaService;
import com.tienda_l.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pruebas") //todos estos métodos van a estar debajo de la ruta /categoria en el url de la página web
public class PruebasController { //esta es la cara de los servidores (clase principal, interactua con las vistas) - Controla todo lo que yo haga en categoria
    
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ProductoService productoService;
    
    @GetMapping("/listado")
    public String listado(Model model){
        var productos=productoService.getProductos(false);
        var categorias=categoriaService.getCategorias(true); //crea una variable categorias que recupera el find all que esta dentro del método get categorias 
        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);
        model.addAttribute("totalCategorias", categorias.size());
        model.addAttribute("totalProductos", productos.size());//size devuelve la cantidad de categorias
        return "/pruebas/listado"; //Aquí se pasa el arraylist - Devuelve la lista que se le quiere mostrar al usuario - el texto es la ubicación del html
    }
    
    @GetMapping("/listado/{idCategoria}")
    public String listadoDetalle(Model model){
        //FALTA PROGRAMAR
        var productos=productoService.getProductos(false);
        var categorias=categoriaService.getCategorias(true); //crea una variable categorias que recupera el find all que esta dentro del método get categorias 
        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);
        model.addAttribute("totalCategorias", categorias.size());
        model.addAttribute("totalProductos", productos.size());//size devuelve la cantidad de categorias
        return "/pruebas/listado"; //Aquí se pasa el arraylist - Devuelve la lista que se le quiere mostrar al usuario - el texto es la ubicación del html
    }
}
