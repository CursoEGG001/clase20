/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package net.local.demo.Biblio.controladores;

import java.util.List;
import net.local.demo.Biblio.entidades.Autor;
import net.local.demo.Biblio.entidades.Editorial;
import net.local.demo.Biblio.entidades.Libro;
import net.local.demo.Biblio.excepciones.MiExcepcion;
import net.local.demo.Biblio.servicios.AutorServicio;
import net.local.demo.Biblio.servicios.EditorialServicio;
import net.local.demo.Biblio.servicios.LibroServicio;
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

@RequestMapping("/view/libro")
public class LibroControlador {

    @Autowired
    LibroServicio libroServicio;
    @Autowired
    AutorServicio autorServicio;
    @Autowired
    EditorialServicio editorialServicio;

    @GetMapping("/registra-libro")
    public String cargarLibro(Model model) {

        List<Autor> listaAutor = autorServicio.listarAutores();
        List<Editorial> listaEditorial = editorialServicio.listarEditoriales();

        model.addAttribute("libro", new Libro());
        model.addAttribute("listaAutor", listaAutor);
        model.addAttribute("listaEditorial", listaEditorial);
        return "libro_registro";
    }

    @GetMapping("/registra-libro/{isbn}")
    public String modificaLibro(@PathVariable Long isbn, Model model) {
        List<Autor> listaAutor = autorServicio.listarAutores();
        List<Editorial> listaEditorial = editorialServicio.listarEditoriales();
        model.addAttribute("libro", libroServicio.getOne(isbn));
        model.addAttribute("listaAutor", listaAutor);
        model.addAttribute("listaEditorial", listaEditorial);
        return "libro_registro";
    }

    @PostMapping("/registro")
    public String guardaLibro(@RequestParam Long isbn, Integer anio, Integer ejemplares, @RequestParam(required = false) Integer ejemplaresprestados, @RequestParam(required = false) Integer ejemplaresrestantes, String titulo, Long autorId, Long editorialId, Model model) throws MiExcepcion {

        Libro libro = null;
        if (libroServicio.buscarLibro(isbn)) {
            libro = libroServicio.getOne(isbn);
        }

        try {

            if (libro != null) {
                if (ejemplares.compareTo(ejemplaresprestados + ejemplaresrestantes) != 0) {
                    throw new MiExcepcion("Ejemplares disponibles no concuerdan");
                }
                libroServicio.modificarLibro(isbn, titulo, ejemplares, ejemplaresprestados, ejemplaresrestantes, autorId, editorialId, anio);

            } else {
                libroServicio.crearLibro(isbn, anio, titulo, ejemplares, autorId, editorialId);
            }

            model.addAttribute("exito", "Se guardó el libro " + isbn);

        } catch (MiExcepcion e) {

            List<Autor> listaAutor = autorServicio.listarAutores();
            List<Editorial> listaEditorial = editorialServicio.listarEditoriales();

            model.addAttribute("listaAutor", listaAutor);
            model.addAttribute("listaEditorial", listaEditorial);
            model.addAttribute("error", "Hubo inconvenientes: " + e.getMessage());

        } finally {

            libro = new Libro();
            libro.setAlta(Boolean.TRUE);
            libro.setAnio(anio);
            libro.setAutorId(autorServicio.getOne(autorId));
            libro.setEditorialId(editorialServicio.getOne(editorialId));
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresprestados(ejemplaresprestados);
            libro.setEjemplaresrestantes(ejemplaresrestantes);
            libro.setTitulo(titulo);
            libro.setIsbn(isbn);

            model.addAttribute("libro", libro);
        }

        return "libro_registro";
    }

    public String page(Model model) {
        model.addAttribute("libro", libroServicio.getOne(0L));
        return "view.name";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Libro> libros = libroServicio.listarLibros();

        modelo.addAttribute("libros", libros);

        return "libro_list.html";
    }
}
