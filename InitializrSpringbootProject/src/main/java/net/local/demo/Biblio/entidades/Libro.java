/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.local.demo.Biblio.entidades;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author pc
 */
@Entity
@Table(name = "libro")
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l"),
    @NamedQuery(name = "Libro.findByIsbn", query = "SELECT l FROM Libro l WHERE l.isbn = :isbn"),
    @NamedQuery(name = "Libro.findByAlta", query = "SELECT l FROM Libro l WHERE l.alta = :alta"),
    @NamedQuery(name = "Libro.findByAnio", query = "SELECT l FROM Libro l WHERE l.anio = :anio"),
    @NamedQuery(name = "Libro.findByEjemplares", query = "SELECT l FROM Libro l WHERE l.ejemplares = :ejemplares"),
    @NamedQuery(name = "Libro.findByEjemplaresprestados", query = "SELECT l FROM Libro l WHERE l.ejemplaresprestados = :ejemplaresprestados"),
    @NamedQuery(name = "Libro.findByEjemplaresrestantes", query = "SELECT l FROM Libro l WHERE l.ejemplaresrestantes = :ejemplaresrestantes"),
    @NamedQuery(name = "Libro.findByTitulo", query = "SELECT l FROM Libro l WHERE l.titulo = :titulo")})
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ISBN")
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
    @Column(name = "TITULO")
    private String titulo;
    @JoinColumn(name = "AUTOR_ID", referencedColumnName = "ID")
    @OneToOne
    private Autor autorId;
    @JoinColumn(name = "EDITORIAL_ID", referencedColumnName = "ID")
    @OneToOne
    private Editorial editorialId;


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
