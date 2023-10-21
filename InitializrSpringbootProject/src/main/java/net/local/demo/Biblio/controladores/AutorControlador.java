/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package net.local.demo.Biblio.controladores;

import java.util.List;
import net.local.demo.Biblio.entidades.Autor;
import net.local.demo.Biblio.servicios.AutorServicio;
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
@RequestMapping("/view/autor")
public class AutorControlador {

    @Autowired
    AutorServicio autorServicio;
    
    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

    
        @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Autor> autores = autorServicio.listarAutores();
        
        modelo.addAttribute("autores", autores);
        
        return "autor_list.html";
    }
}
