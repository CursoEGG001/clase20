/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package net.local.demo.Biblio.controladores;

import java.util.List;
import net.local.demo.Biblio.entidades.Autor;
import net.local.demo.Biblio.excepciones.MiExcepcion;
import net.local.demo.Biblio.servicios.AutorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This controller handles operations related to authors.
 * It provides endpoints for listing authors, initiating author registration, and managing author details.
 * It utilizes the `AutorServicio` service layer to interact with the underlying data.
 * @author pc
 */
@Controller
@RequestMapping("/view/autor")
public class AutorControlador {

    @Autowired
    AutorServicio autorServicio;

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Autor> autores = autorServicio.listarAutores();

        modelo.addAttribute("autores", autores);

        return "autor_list.html";
    }

    @GetMapping("/registra-autor")
    public String iniciaRegistro(ModelMap modelo) {
        return "autor_registro.html";
    }

    @GetMapping("/registra-autor/{id}")
    public String cambiaRegistro(@PathVariable(required = false) Long id, ModelMap modelo) {

        modelo.addAttribute("id", id);
        modelo.addAttribute("nombre", autorServicio.getOne(id).getNombre());
        return "autor_registro.html";
    }

    @PostMapping("/registro")
    public String registra(@RequestParam(required = false) Long id, @RequestParam String nombre, ModelMap modelo) throws MiExcepcion {

        try {
            if (id == null) {
                autorServicio.crearAutor(nombre);
            } else {
                autorServicio.modificarAutor(nombre, id);
            }
            modelo.addAttribute("exito", "Guardado el autor");
        } catch (MiExcepcion e) {

            modelo.addAttribute("nombre", nombre);
            modelo.addAttribute("id", id);
            modelo.addAttribute("error", e.getMessage());

            return "autor_registro.html";
        }

        return "redirect:/view/autor/lista";
    }
}
