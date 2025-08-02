/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package net.local.demo.Biblio.controladores;

import java.math.BigInteger;
import java.util.List;
import net.local.demo.Biblio.entidades.Cliente;
import net.local.demo.Biblio.excepciones.MiExcepcion;
import net.local.demo.Biblio.servicios.ClienteServicio;
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
 * This class handles client-related operations, including listing, registering, and modifying client data.
 * It utilizes dependency injection to interact with a `ClienteServicio` service.
 * The class exposes REST endpoints for managing clients.
 * @author pc
 */
@Controller
@RequestMapping("/view/cliente")
public class ClienteControlador {

    @Autowired
    ClienteServicio clienteServicio;

    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

    @GetMapping("/lista")
    public String listar(ModelMap modelo) {

        List<Cliente> clientes = clienteServicio.ListarClientes();

        modelo.addAttribute("clientes", clientes);

        return "cliente_list.html";
    }

    @GetMapping("/registra-cliente")
    public String iniciaRegistro(ModelMap modelo) {
        Cliente cliente = new Cliente();
        modelo.addAttribute("cliente", cliente);
        return "cliente_registro.html";
    }

    @GetMapping("/registra-cliente/{id}")
    public String cambiaRegistro(@PathVariable(required = false) Long id, ModelMap modelo) {

        Cliente cliente = null;
        if (id != null) {
            cliente = clienteServicio.getOne(id);
        } else {
            cliente = new Cliente();
        }

        modelo.addAttribute("cliente", cliente);

        return "cliente_registro.html";
    }

    @PostMapping("/registro")
    public String registra(@RequestParam(required = false) Long id, @RequestParam String apellido, @RequestParam BigInteger documento, @RequestParam String nombre, @RequestParam String telefono, ModelMap modelo) throws MiExcepcion {

        Cliente cliente = new Cliente();
        try {
            if (id == null) {
                clienteServicio.CrearCliente(apellido, documento, nombre, telefono);
            } else {
                cliente = clienteServicio.getOne(id);
                clienteServicio.ModificarCliente(id, apellido, documento, nombre, telefono);
            }
            modelo.addAttribute("exito", "Guardado el autor");
        } catch (MiExcepcion e) {

            modelo.addAttribute("cliente", cliente);

            modelo.addAttribute("error", e.getMessage());

            return "cliente_registro.html";
        }

        return "redirect:/view/cliente/lista";
    }
}
