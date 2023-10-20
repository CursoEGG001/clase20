/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package net.local.demo.Biblio.servicios;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import net.local.demo.Biblio.entidades.Cliente;
import net.local.demo.Biblio.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pc
 */
@Service
public class ClienteServicio {
    
    @Autowired
    ClienteRepositorio clienteRepositorio;
    
    public void CrearCliente(String apellido,BigInteger documento,String nombre,String telefono) {
        
        Cliente cliente = new Cliente();
        cliente.setApellido(apellido);
        cliente.setDocumento(documento);
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        clienteRepositorio.save(cliente);
                
    }
    
    public List<Cliente> ListarClientes(){
        List<Cliente> clientes = new ArrayList<>();
        clientes = clienteRepositorio.findAll();
        return clientes;
    }
    
    public Cliente getOne(Long id){
        return clienteRepositorio.getReferenceById(id);
    }
    
}
