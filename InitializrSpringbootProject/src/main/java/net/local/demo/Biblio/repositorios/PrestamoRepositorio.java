/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package net.local.demo.Biblio.repositorios;

import jakarta.persistence.TemporalType;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Date;
import java.util.List;
import net.local.demo.Biblio.entidades.Cliente;
import net.local.demo.Biblio.entidades.Libro;
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

    // @Query para encontrar todos los préstamos que vencen hoy
    @Query("SELECT p FROM Prestamo p WHERE p.fechadevolucion = CURRENT_DATE")
    List<Prestamo> findAllPrestamosVenciendoHoy();

    Instant sevenDays = new Date().toInstant().plus(Duration.ofDays(7));

    // @Query para encontrar todos los préstamos que vencen en los próximos 7 días
    @Query("SELECT p FROM Prestamo p WHERE p.fechadevolucion BETWEEN CURRENT_DATE AND :sevenDays")
    List<Prestamo> findAllPrestamosVenciendoProximos7Dias();

    // @Query para encontrar todos los préstamos de un cliente específico
    @Query("SELECT p FROM Prestamo p WHERE p.clienteId = :clienteId")
    List<Prestamo> findAllPrestamosByClienteId(Cliente clienteId);

    // @Query para encontrar todos los préstamos de un libro específico
    @Query("SELECT p FROM Prestamo p WHERE p.libroIsbn = :libroIsbn")
    List<Prestamo> findAllPrestamosByLibroIsbn(Libro libroIsbn);

}
