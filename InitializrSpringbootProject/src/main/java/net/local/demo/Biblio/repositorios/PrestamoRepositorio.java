/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package net.local.demo.Biblio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author pc
 */
public interface PrestamoRepositorio extends JpaRepository<PrestamoRepositorio, Long> {
    
}
