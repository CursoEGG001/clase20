/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package net.local.demo.Biblio.repositorios;

import jakarta.persistence.Id;
import net.local.demo.Biblio.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pc
 */
@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    
}
