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
import net.local.demo.Biblio.repositorios.ClienteRepositorio;
import net.local.demo.Biblio.repositorios.LibroRepositorio;
import net.local.demo.Biblio.repositorios.PrestamoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Service
public class PrestamoServicio {

    @Autowired
    PrestamoRepositorio prestamoRepositorio;
    @Autowired
    LibroRepositorio libroRepositorio;
    @Autowired
    ClienteRepositorio clienteRepositorio;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void crearPrestamo(Date fechaprestamo, Date fechadevolucion, Long clienteId, Long libroIsbn) throws MiExcepcion {
        validar(fechaprestamo, fechadevolucion, clienteId, libroIsbn);

        Optional<Cliente> respuestaCliente = clienteRepositorio.findById(clienteId);
        Optional<Libro> respuestaLibro = libroRepositorio.findById(libroIsbn);

        Cliente cliente = new Cliente();
        Libro libro = new Libro();

        if (respuestaCliente.isPresent()) {
            cliente = respuestaCliente.get();
        }

        if (respuestaLibro.isPresent()) {
            libro = respuestaLibro.get();
        }

        Prestamo prestamo = new Prestamo();

        prestamo.setClienteId(cliente);
        prestamo.setFechadevolucion(fechadevolucion);
        prestamo.setFechaprestamo(fechaprestamo);
        prestamo.setLibroIsbn(libro);
        try {
            prestamoRepositorio.save(prestamo);
        } catch (Exception e) {
            throw new MiExcepcion("Error al crear préstamo: " + e.getMessage());
        }

    }

    public List<Prestamo> listarPrestamos() throws MiExcepcion {
        List<Prestamo> prestamos = new ArrayList();
        try {
            prestamos = prestamoRepositorio.findAll();
        } catch (Exception e) {
            throw new MiExcepcion("Error al listar préstamos: " + e.getMessage());
        }

        return prestamos;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void modificarPrestamo(Date fechaprestamo, Date fechadevolucion, Long clienteId, Long libroIsbn, Long id) throws MiExcepcion {
        validar(fechaprestamo, fechadevolucion, clienteId, libroIsbn);

        System.out.println("Entrada a cambiar-- ID:" + id + " Libro: " + libroIsbn + " Cliente: " + clienteId);

        Optional<Prestamo> respuesta = prestamoRepositorio.findById(id);
        Optional<Libro> respuestaLibro = libroRepositorio.findById(libroIsbn);
        Optional<Cliente> respuestaCliente = clienteRepositorio.findById(clienteId);

        Libro libro = new Libro();
        Cliente cliente = new Cliente();

        if (respuesta.isPresent()) {

            Prestamo prestamo = respuesta.get();
            try {
                if (respuestaLibro.isPresent()) {
                    libro = respuestaLibro.get();
                    prestamo.setLibroIsbn(libro);
                }
                if (respuestaCliente.isPresent()) {
                    cliente = respuestaCliente.get();
                    prestamo.setClienteId(cliente);
                }

                System.out.println("Cargando modificación..." + prestamo.getLibroIsbn() + " " + prestamo.getClienteId());

                prestamo.setFechadevolucion(fechadevolucion);
                prestamo.setFechaprestamo(fechaprestamo);

                prestamoRepositorio.save(prestamo);
            } catch (Exception e) {
                throw new MiExcepcion("Error al modificar préstamo: " + e.getMessage());
            }
        }
    }

    public Prestamo getOne(Long id) throws MiExcepcion {
        try {
            return prestamoRepositorio.getOne(id);
        } catch (Exception e) {
            throw new MiExcepcion("Error al obtener préstamo: " + e.getMessage());
        }
    }

    public Boolean buscarPrestamo(Long id) {
        return prestamoRepositorio.existsById(id);
    }

    private void validar(Date unafecha, Date otrafecha, Long uncliente, Long unLibro) throws MiExcepcion {

        if (unafecha == null) {
            throw new MiExcepcion("la fecha no puede ser nulo");
        }
        if (otrafecha == null) {
            throw new MiExcepcion("la fecha no puede ser nulo");
        }
        if (uncliente == null) {
            throw new MiExcepcion("el cliente no puede ser nulo");
        }
        if (unLibro == null) {
            throw new MiExcepcion("el libro no puede ser nulo");
        }
    }
}
