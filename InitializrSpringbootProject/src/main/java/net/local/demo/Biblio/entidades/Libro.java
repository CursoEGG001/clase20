/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.local.demo.Biblio.entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "libro")
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ISBN", unique = true,nullable = false)
    private Long isbn;
    @Column(name = "ALTA")
    private Boolean alta;
    @Column(name = "ANIO")
    private Integer anio;
    @Column(name = "EJEMPLARES")
    private Integer ejemplares;
    @Column(name = "EJEMPLARESPRESTADOS")
    private Integer ejemplaresprestados;
    @Column(name = "EJEMPLARESRESTANTES")
    private Integer ejemplaresrestantes;
    @Column(name = "TITULO", unique = true)
    private String titulo;
    @JoinColumn(name = "AUTOR_ID", referencedColumnName = "ID")
    @OneToOne
    private Autor autorId;
    @JoinColumn(name = "EDITORIAL_ID", referencedColumnName = "ID")
    @OneToOne
    private Editorial editorialId;

    @OneToMany(mappedBy = "libroIsbn", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Prestamo> prestamo;

    public List<Prestamo> getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(List<Prestamo> prestamo) {
        this.prestamo = prestamo;
    }

    public Libro() {
    }

    public Libro(Long isbn) {
        this.isbn = isbn;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getEjemplaresprestados() {
        return ejemplaresprestados;
    }

    public void setEjemplaresprestados(Integer ejemplaresprestados) {
        this.ejemplaresprestados = ejemplaresprestados;
    }

    public Integer getEjemplaresrestantes() {
        return ejemplaresrestantes;
    }

    public void setEjemplaresrestantes(Integer ejemplaresrestantes) {
        this.ejemplaresrestantes = ejemplaresrestantes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutorId() {
        return autorId;
    }

    public void setAutorId(Autor autorId) {
        this.autorId = autorId;
    }

    public Editorial getEditorialId() {
        return editorialId;
    }

    public void setEditorialId(Editorial editorialId) {
        this.editorialId = editorialId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.local.demo.Biblio.entidades.Libro[ isbn=" + isbn + " ]";
    }

}
