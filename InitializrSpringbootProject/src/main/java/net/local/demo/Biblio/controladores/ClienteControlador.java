/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package net.local.demo.Biblio.controladores;

import java.util.List;
import net.local.demo.Biblio.entidades.Cliente;
import net.local.demo.Biblio.servicios.ClienteServicio;
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
@RequestMapping("/view/cliente")
public class ClienteControlador {
    
    @Autowired
    ClienteServicio clienteServicio;

    public String page(Model model) {
        model.addAttribute("attribute", "value");
        return "view.name";
    }

            @GetMapping("/lista")
    public String listar(ModelMap modelo){
        
        List<Cliente> clientes = clienteServicio.ListarClientes();
        
        modelo.addAttribute("clientes", clientes);
        
        return "cliente_list.html";
    }
}
