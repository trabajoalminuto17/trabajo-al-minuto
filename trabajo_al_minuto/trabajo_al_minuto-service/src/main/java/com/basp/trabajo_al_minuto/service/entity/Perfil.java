/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author BASP
 */
@Entity
@Table(name = "perfil")
@NamedQueries({
    @NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p")})
public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "perfil_id")
    private Long perfilId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2147483647)
    @Column(name = "salario")
    private String salario;
    @Size(max = 2147483647)
    @Column(name = "requerimientos")
    private String requerimientos;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil")
    private List<Oferta> ofertaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "perfil")
    private List<PerfilHasPrueba> perfilHasPruebaList;
    @JoinColumn(name = "area", referencedColumnName = "catalogo_id")
    @ManyToOne(optional = false)
    private Catalogo area;

    public Perfil() {
    }

    public Perfil(Long perfilId) {
        this.perfilId = perfilId;
    }

    public Perfil(Long perfilId, String titulo) {
        this.perfilId = perfilId;
        this.titulo = titulo;
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getRequerimientos() {
        return requerimientos;
    }

    public void setRequerimientos(String requerimientos) {
        this.requerimientos = requerimientos;
    }

    public List<Oferta> getOfertaList() {
        return ofertaList;
    }

    public void setOfertaList(List<Oferta> ofertaList) {
        this.ofertaList = ofertaList;
    }

    public List<PerfilHasPrueba> getPerfilHasPruebaList() {
        return perfilHasPruebaList;
    }

    public void setPerfilHasPruebaList(List<PerfilHasPrueba> perfilHasPruebaList) {
        this.perfilHasPruebaList = perfilHasPruebaList;
    }

    public Catalogo getArea() {
        return area;
    }

    public void setArea(Catalogo area) {
        this.area = area;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilId != null ? perfilId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perfil)) {
            return false;
        }
        Perfil other = (Perfil) object;
        if ((this.perfilId == null && other.perfilId != null) || (this.perfilId != null && !this.perfilId.equals(other.perfilId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.Perfil[ perfilId=" + perfilId + " ]";
    }
    
}
