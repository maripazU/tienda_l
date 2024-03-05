package com.tienda_l.controller;

import com.tienda_l.domain.Producto;
import com.tienda_l.service.CategoriaService;
import com.tienda_l.service.ProductoService;
import com.tienda_l.service.impl.FirebaseStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

// Esta clase es la que controla todo lo relacionado con producto
@Controller
// Todos los metodos van a estar debajo de producto
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(true);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);
        return "producto/listado";
    }

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageImpl;

    @PostMapping("/guardar")
    public String guardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            //Si estamos acá hay que guardar la foto
            productoService.saveProducto(producto);
            producto.setRutaImagen(
                    firebaseStorageImpl.cargaImagen(imagenFile,
                            "producto", producto.getIdProducto()));
            //aqui se define una ruta en el firebase, donde se guarda la información
        }
        productoService.saveProducto(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String elimina(Producto producto) {
        productoService.deleteProducto(producto);
        return "redirect:/producto/listado";
    }
    
    @GetMapping("/modificar/{idProducto}")
    public String modifica(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
        var categorias=categoriaService.getCategorias(true); 
        model.addAttribute("categorias", categorias);
        return "/producto/modifica";
    }
}