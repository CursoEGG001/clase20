/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package net.local.demo.Biblio.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import net.local.demo.Biblio.entidades.Cliente;
import net.local.demo.Biblio.entidades.Libro;
import net.local.demo.Biblio.entidades.Prestamo;
import net.local.demo.Biblio.excepciones.MiExcepcion;
import net.local.demo.Biblio.repositorios.PrestamoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Service
public class PrestamoServicio {
    
    @Autowired
    PrestamoRepositorio prestamoRepositorio;

    @Transactional
    public void crearPrestamo(Date fechaprestamo,Date fechadevolucion,Cliente cliente,Libro libro) throws MiExcepcion {
        validar(fechaprestamo,fechadevolucion,cliente,libro);
        Prestamo prestamo = new Prestamo();
        prestamo.setClienteId(cliente);
        prestamo.setFechadevolucion(fechadevolucion);
        prestamo.setFechaprestamo(fechaprestamo);
        prestamo.setLibroIsbn(libro);
        prestamoRepositorio.save(prestamo);

    }

    public List<Prestamo> listarAutores() {
        List<Prestamo> prestamos = new ArrayList();
        prestamos = prestamoRepositorio.findAll();
        return prestamos;
    }

    @Transactional
    public void modificarAutor(Date fechaprestamo,Date fechadevolucion,Cliente cliente,Libro libro, Long id) throws MiExcepcion {
        validar(fechaprestamo,fechadevolucion,cliente,libro);
        Optional<Prestamo> respuesta = prestamoRepositorio.findById(id);
       if (respuesta.isPresent()) {
            Prestamo prestamo = respuesta.get();
            prestamo.setClienteId(cliente);
            prestamo.setFechadevolucion(fechadevolucion);
            prestamo.setFechaprestamo(fechaprestamo);
            prestamo.setLibroIsbn(libro);
            prestamoRepositorio.save(prestamo);
        }
    }

    public Prestamo getOne(Long id) {
        return prestamoRepositorio.getOne(id);
    }

    private void validar(Date unafecha,Date otrafecha,Cliente uncliente,Libro unLibro) throws MiExcepcion {

        if (unafecha == null) {
            throw new MiExcepcion("la fecha no puede ser nulo");
        }
        if (otrafecha == null) {
            throw new MiExcepcion("la fecha no puede ser nulo");
        }
        if (uncliente == null) {
            throw new MiExcepcion("el nombre no puede ser nulo");
        }
        if (unLibro == null) {
            throw new MiExcepcion("el nombre no puede ser nulo");
        }
    }
}
