/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package net.local.demo.Biblio.controladores;

import java.util.Date;
import java.util.List;
import net.local.demo.Biblio.entidades.Cliente;
import net.local.demo.Biblio.entidades.Libro;
import net.local.demo.Biblio.entidades.Prestamo;
import net.local.demo.Biblio.excepciones.MiExcepcion;
import net.local.demo.Biblio.servicios.ClienteServicio;
import net.local.demo.Biblio.servicios.LibroServicio;
import net.local.demo.Biblio.servicios.PrestamoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

@RequestMapping("/view/prestamo")
public class PrestamoControlador {

    @Autowired
    PrestamoServicio prestamoServicio;
    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    LibroServicio libroServicio;

    @GetMapping("/registra-prestamo")
    public String cargaPrestamo(Model model) {

        List<Cliente> listaClientes = clienteServicio.ListarClientes();
        List<Libro> listaLibros = libroServicio.listarLibros();
        Prestamo prestamo = new Prestamo();

        model.addAttribute("listaClientes", listaClientes);
        model.addAttribute("listaLibros", listaLibros);
        model.addAttribute("prestamo", prestamo);
        return "prestamo_registro";
    }

    @GetMapping("/registra-prestamo/{id}")
    public String modificaPrestamo(@PathVariable Long id, Model model) throws MiExcepcion {

        List<Cliente> listaClientes = clienteServicio.ListarClientes();
        List<Libro> listaLibros = libroServicio.listarLibros();
        Prestamo prestamo = prestamoServicio.getOne(id);

        model.addAttribute("listaClientes", listaClientes);
        model.addAttribute("listaLibros", listaLibros);
        model.addAttribute("prestamo", prestamo);

        return "prestamo_registro";
    }

    @PostMapping("/registro")
    public String guardaPrestamo(
            @RequestParam(required = false) Long id,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date fechadevolucion,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date fechaprestamo,
            @RequestParam Long clienteId,
            @RequestParam Long libroIsbn,
            Model model
    ) throws MiExcepcion {
        Prestamo prestamo = new Prestamo();

        List<Cliente> listaClientes = clienteServicio.ListarClientes();
        List<Libro> listaLibros = libroServicio.listarLibros();
        
        try {
            if (id != null && prestamoServicio.buscarPrestamo(id)) {
                prestamo = prestamoServicio.getOne(id);
            }
            
            if (prestamo.getId() != null) {
                prestamoServicio.modificarPrestamo(fechaprestamo, fechadevolucion, clienteId, libroIsbn, id);
                model.addAttribute("exito", "Se guardó el prestamo " + (prestamo.getId() != null ? id : "como nuevo"));
            } else {
                prestamoServicio.crearPrestamo(fechaprestamo, fechadevolucion, clienteId, libroIsbn);
                prestamo.setFechadevolucion(fechadevolucion);
                prestamo.setFechaprestamo(fechaprestamo);
                prestamo.setLibroIsbn(libroServicio.getOne(libroIsbn));
                
                 model.addAttribute("exito", "Se guardó el prestamo nuevo");

            }
           

        } catch (MiExcepcion e) {
            model.addAttribute("error", "Falló el guardado :" + e.getMessage());
        }

        model.addAttribute("listaClientes", listaClientes);
        model.addAttribute("listaLibros", listaLibros);
        model.addAttribute("prestamo", prestamo);
        return "prestamo_registro";

    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) throws MiExcepcion {

        List<Prestamo> prestamos = prestamoServicio.listarPrestamos();
        modelo.addAttribute("prestamos", prestamos);

        return "prestamo_list";
    }
}
