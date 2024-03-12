package com.tienda_l.controller;

import com.tienda_l.domain.Categoria;
import com.tienda_l.service.CategoriaService;
import com.tienda_l.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pruebas") //todos estos métodos van a estar debajo de la ruta /categoria en el url de la página web
public class PruebasController { //esta es la cara de los servidores (clase principal, interactua con las vistas) - Controla todo lo que yo haga en categoria

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(true); //crea una variable categorias que recupera el find all que esta dentro del método get categorias 
        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);
        model.addAttribute("totalCategorias", categorias.size());
        //size devuelve la cantidad de categorias
        return "/pruebas/listado"; //Aquí se pasa el arraylist - Devuelve la lista que se le quiere mostrar al usuario - el texto es la ubicación del html
    }

    @GetMapping("/listado/{idCategoria}")
    public String listadoDetalle(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria); //carga el objeto completo de la base de datos
        var productos = categoria.getProductos();
        var categorias = categoriaService.getCategorias(true); //crea una variable categorias que recupera el find all que esta dentro del método get categorias 
        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);
        return "/pruebas/listado"; //Aquí se pasa el arraylist - Devuelve la lista que se le quiere mostrar al usuario - el texto es la ubicación del html
    }

    @GetMapping("/listado2")
    public String listado2(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        return "/pruebas/listado2";
    }
    
    @PostMapping("/query1")
    public String consultaQuery1(@RequestParam(value="precioInf") double precioInf, @RequestParam(value="precioSup") double precioSup, Model model){
        var productos = productoService.metodoQuery(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "pruebas/listado2";
    }
    
    @PostMapping("/query2")
    public String consultaQuery2(@RequestParam(value="precioInf") double precioInf, @RequestParam(value="precioSup") double precioSup, Model model){
        var productos = productoService.metodoJPQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "pruebas/listado2";
    }
    
    @PostMapping("/query3")
    public String consultaQuery3(@RequestParam(value="precioInf") double precioInf, @RequestParam(value="precioSup") double precioSup, Model model){
        var productos = productoService.metodoNativo(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);
        return "pruebas/listado2";
    }
}
