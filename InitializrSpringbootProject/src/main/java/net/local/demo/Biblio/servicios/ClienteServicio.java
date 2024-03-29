/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package net.local.demo.Biblio.servicios;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import net.local.demo.Biblio.entidades.Cliente;
import net.local.demo.Biblio.excepciones.MiExcepcion;
import net.local.demo.Biblio.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Service
public class ClienteServicio {

    @Autowired
    ClienteRepositorio clienteRepositorio;

    @Transactional
    public void CrearCliente(String apellido, BigInteger documento, String nombre, String telefono) throws MiExcepcion {

        validar(apellido, documento, nombre, telefono);
        Cliente cliente = new Cliente();
        cliente.setApellido(apellido);
        cliente.setDocumento(documento);
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        clienteRepositorio.save(cliente);

    }

    @Transactional
    public void ModificarCliente(Long id, String apellido, BigInteger documento, String nombre, String telefono) throws MiExcepcion {

        validar(apellido, documento, nombre, telefono);

        Cliente cliente = clienteRepositorio.getReferenceById(id);
        cliente.setApellido(apellido);
        cliente.setDocumento(documento);
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        clienteRepositorio.save(cliente);

    }

    @Transactional(readOnly = true)
    public List<Cliente> ListarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes = clienteRepositorio.findAll();
        return clientes;
    }

    @Transactional(readOnly = true)
    public Cliente getOne(Long id) {
        return clienteRepositorio.getReferenceById(id);
    }

    private void validar(String apellido, BigInteger documento, String nombre, String telefono) throws MiExcepcion {

        if (documento == null) {
            throw new MiExcepcion("el numero no puede ser nulo o estar vacío.");
        }
        if (apellido.isEmpty() || apellido == null) {
            throw new MiExcepcion("el apellido no puede ser nulo o estar vacío.");
        }
        if (telefono.isEmpty() || telefono == null || telefono.matches("^\\d{9}$")) {
            throw new MiExcepcion("el telefono no puede ser nulo, estar vacio o tener menos de diez numeros.");
        }
        if (nombre.isEmpty() || nombre == null) {
            throw new MiExcepcion("el nombre no puede ser nulo o estar vacío");
        }

    }

}
