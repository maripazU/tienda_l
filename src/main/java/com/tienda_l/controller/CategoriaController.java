package com.tienda_l.controller;


import com.tienda_l.domain.Categoria;
import com.tienda_l.service.CategoriaService;
import com.tienda_l.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/categoria") //todos estos métodos van a estar debajo de la ruta /categoria en el url de la página web
public class CategoriaController { //esta es la cara de los servidores (clase principal, interactua con las vistas) - Controla todo lo que yo haga en categoria
    
    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping("/listado")
    public String listado(Model model){
        var categorias=categoriaService.getCategorias(true); //crea una variable categorias que recupera el find all que esta dentro del método get categorias 
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size()); //size devuelve la cantidad de categorias
        return "/categoria/listado"; //Aquí se pasa el arraylist - Devuelve la lista que se le quiere mostrar al usuario - el texto es la ubicación del html
    }
    
    @Autowired
    private FirebaseStorageServiceImpl firebaseStrorageImpl;
    
    @PostMapping("/guardar")
    public String guardar(Categoria categoria, @RequestParam("imagenFile") MultipartFile imagenFile){ //para enviar una imagen siempre se usa multipartFile
        if (!imagenFile.isEmpty()) {
            //si estamos acá hay q guardar la foto
            categoriaService.saveCategoria(categoria);
            categoria.setRutaImagen(
            firebaseStrorageImpl.cargaImagen(imagenFile, "categoria", categoria.getIdCategoria()) //esto define una ruta en el file base donde quedó guardada la información
            );
        }
        categoriaService.saveCategoria(categoria);
        return "redirect:/categoria/listado";
    }
    
    @GetMapping("/eliminar/{idCategoria}") //se pone como se está llamando en la base de datos el id entonces genéricamente se pasa por el valor que le pase la base de datos
    public String elimina(Categoria categoria){ //automáticamente se le asigna a este objeto categoria el idCategoria que pasa la base de datos
        categoriaService.deleteCategoria(categoria);
        return "redirect:/categoria/listado"; //se refresca el listado de la categoria
    }
    
    @GetMapping("/modificar/{idCategoria}") //se pone como se está llamando en la base de datos el id entonces genéricamente se pasa por el valor que le pase la base de datos
    public String modifica(Categoria categoria, Model model){ //automáticamente se le asigna a este objeto categoria el idCategoria que pasa la base de datos
        //MODEL: sirve para pasar info a las páginas html
        categoria = categoriaService.getCategoria(categoria); //se recupera toda la info en este objeto categoria, si existe por supuesto
        model.addAttribute("categoria", categoria);
        return "/categoria/modifica"; //se refresca el listado de la categoria
    }
    
}
