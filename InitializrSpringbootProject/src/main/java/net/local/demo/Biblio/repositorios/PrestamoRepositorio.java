/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package net.local.demo.Biblio.repositorios;

import java.util.Date;
import java.util.List;
import net.local.demo.Biblio.entidades.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, Long> {

    @Query("SELECT p FROM Prestamo p WHERE p.fechadevolucion = :fechadevolucion")
    public List<Prestamo> findByFechadevolucion(Date fechadevolucion);

    @Query("SELECT p FROM Prestamo p WHERE p.fechaprestamo = :fechaprestamo")
    public List<Prestamo> findByFechaprestamo(Date fechaprestamo);
}
