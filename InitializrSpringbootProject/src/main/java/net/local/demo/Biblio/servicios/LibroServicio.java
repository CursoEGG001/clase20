/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package net.local.demo.Biblio.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.local.demo.Biblio.entidades.Autor;
import net.local.demo.Biblio.entidades.Editorial;
import net.local.demo.Biblio.entidades.Libro;
import net.local.demo.Biblio.excepciones.MiExcepcion;
import net.local.demo.Biblio.repositorios.AutorRepositorio;
import net.local.demo.Biblio.repositorios.EditorialRepositorio;
import net.local.demo.Biblio.repositorios.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, Integer anio, String titulo, Integer ejemplares, Long idAutor, Long idEditorial) throws MiExcepcion {

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {

            autor = respuestaAutor.get();
        }

        if (respuestaEditorial.isPresent()) {

            editorial = respuestaEditorial.get();
        }

        Libro libro = new Libro();

        libro.setIsbn(isbn);
        libro.setAnio(anio);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresprestados(0);
        libro.setEjemplaresrestantes(ejemplares);
        libro.setAlta(Boolean.TRUE);
        libro.setAutorId(autor);
        libro.setEditorialId(editorial);
        libroRepositorio.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList();

        libros = libroRepositorio.findAll();

        return libros;
    }

    @Transactional
    public void modificarLibro(Long isbn, String titulo, Integer ejemplares, Integer ejemplaresprestados, Integer ejemplaresrestantes, Long idAutor, Long idEditorial, Integer anio) throws MiExcepcion {

        validar(isbn, titulo, ejemplares, idAutor, idEditorial);

        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respuestaAutor.isPresent()) {

            autor = respuestaAutor.get();
        }

        if (respuestaEditorial.isPresent()) {

            editorial = respuestaEditorial.get();
        }

        if (respuesta.isPresent()) {

            Libro libro = respuesta.get();

            libro.setTitulo(titulo);

            libro.setEjemplares(ejemplares);

            libro.setEjemplaresprestados(ejemplaresprestados);

            libro.setEjemplaresrestantes(ejemplaresrestantes);

            libro.setAutorId(autor);

            libro.setAnio(anio);

            libro.setEditorialId(editorial);

            libroRepositorio.save(libro);

        }
    }

    public Boolean buscarLibro(Long isbn) {
        return libroRepositorio
                .existsById(isbn);
    }

    @Transactional(readOnly = true)
    public Libro getOne(Long isbn) {
        return libroRepositorio.
                getReferenceById(isbn);
    }

    private void validar(Long isbn, String titulo, Integer ejemplares, Long idAutor, Long idEditorial) throws MiExcepcion {

        if (isbn == null) {
            throw new MiExcepcion("el isbn no puede ser nulo"); //
        }
        if (titulo.isEmpty() || titulo == null) {
            throw new MiExcepcion("el titulo no puede ser nulo o estar vacio");
        }
        if (ejemplares == null) {
            throw new MiExcepcion("ejemplares no puede ser nulo");
        }
        if (idAutor == null) {
            throw new MiExcepcion("el Autor no puede ser nulo o estar vacio");
        }

        if (idEditorial == null) {
            throw new MiExcepcion("La Editorial no puede ser nula o estar vacia");
        }
    }

}
