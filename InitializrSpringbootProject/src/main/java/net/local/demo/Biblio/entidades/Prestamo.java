/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.local.demo.Biblio.entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "prestamo")
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p"),
    @NamedQuery(name = "Prestamo.findById", query = "SELECT p FROM Prestamo p WHERE p.id = :id"),
    @NamedQuery(name = "Prestamo.findByCliente", query = "SELECT p FROM Prestamo p WHERE p.clienteId = :clienteId"),
    @NamedQuery(name = "Prestamo.findByFechadevolucion", query = "SELECT p FROM Prestamo p WHERE p.fechadevolucion = :fechadevolucion"),
    @NamedQuery(name = "Prestamo.findByFechaprestamo", query = "SELECT p FROM Prestamo p WHERE p.fechaprestamo = :fechaprestamo"),
    @NamedQuery(name = "Prestamo.findByLibroIsbn", query = "SELECT p FROM Prestamo p WHERE p.libroIsbn = :libroIsbn")})
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "FECHADEVOLUCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechadevolucion;
    @Column(name = "FECHAPRESTAMO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaprestamo;
    private Cliente clienteId;
    private Libro libroIsbn;

    public Prestamo() {
    }

    public Prestamo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public Date getFechaprestamo() {
        return fechaprestamo;
    }

    public void setFechaprestamo(Date fechaprestamo) {
        this.fechaprestamo = fechaprestamo;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    public Libro getLibroIsbn() {
        return libroIsbn;
    }

    public void setLibroIsbn(Libro libroIsbn) {
        this.libroIsbn = libroIsbn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.local.demo.Biblio.entidades.Prestamo[ id=" + id + " ]";
    }

}
