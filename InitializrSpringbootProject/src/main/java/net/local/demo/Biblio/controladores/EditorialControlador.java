/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package net.local.demo.Biblio.controladores;

import java.util.List;
import net.local.demo.Biblio.entidades.Editorial;
import net.local.demo.Biblio.excepciones.MiExcepcion;
import net.local.demo.Biblio.servicios.EditorialServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author pc
 */
@Controller
@RequestMapping("/view/editorial")
public class EditorialControlador {

    @Autowired
    EditorialServicio editorialServicio;

    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Editorial> editoriales = editorialServicio.listarEditoriales();

        modelo.addAttribute("editoriales", editoriales);

        return "editorial_list.html";
    }

    @GetMapping("/registra-editorial")
    public String registroEditorial(ModelMap modelo) {

        return "editorial_registro.html";
    }

    @GetMapping("/registra-editorial/{id}")
    public String registraEditorial(@PathVariable(required = false) Long id, ModelMap modelo) {

        Editorial editorial = editorialServicio.getOne(id);

        modelo.addAttribute("id", editorial.getId());
        modelo.addAttribute("nombre", editorial.getNombre());

        return "editorial_registro.html";
    }

    @PostMapping("/registro")
    public String registra(@RequestParam(required = false) Long id, @RequestParam String nombre, ModelMap modelo) throws MiExcepcion {
        Editorial editorial = null;
        try {
            if (id == null) {
                editorial = editorialServicio.crearEditorial(nombre);
                modelo.addAttribute("exito", "Guardado con Id: " + editorial.getId());
            } else {
                editorialServicio.modificarEditorial(id, nombre);
                modelo.addAttribute("exito", "Se modificó Id: " + id);
            }
           
        } catch (MiExcepcion e) {
            modelo.addAttribute("id", id);
            modelo.addAttribute("nombre", nombre);
            modelo.addAttribute("error", "Hubo un inconveniente:" + e.getMessage());
        }
        return "editorial_registro.html";
    }
}
