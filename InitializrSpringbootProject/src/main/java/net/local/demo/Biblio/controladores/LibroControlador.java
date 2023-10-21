/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package net.local.demo.Biblio.controladores;

import java.util.List;
import net.local.demo.Biblio.entidades.Libro;
import net.local.demo.Biblio.servicios.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author pc
 */
@Controller

@RequestMapping("/view/libro")
public class LibroControlador {
    
    @Autowired
    LibroServicio libroServicio;

    public String page(Model model) {
        model.addAttribute("libro", libroServicio.getOne(0L));
        return "view.name";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Libro> libros = libroServicio.listarLibros();
        
        modelo.addAttribute("libros", libros);
        
        return "libro_list.html";
    }
}

