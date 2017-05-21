/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author BASP
 */
@Entity
@Table(name = "prueba")
@NamedQueries({
    @NamedQuery(name = "Prueba.findAll", query = "SELECT p FROM Prueba p")})
public class Prueba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prueba_id")
    private Long pruebaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "limite_tiempo")
    @Temporal(TemporalType.TIME)
    private Date limiteTiempo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje_minimo")
    private double porcentajeMinimo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "area", referencedColumnName = "catalogo_id")
    @ManyToOne(optional = false)
    private Catalogo area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pruebaPruebaId")
    private List<Pregunta> preguntaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prueba")
    private List<PerfilHasPrueba> perfilHasPruebaList;

    public Prueba() {
    }

    public Prueba(Long pruebaId) {
        this.pruebaId = pruebaId;
    }

    public Prueba(Long pruebaId, String nombre, Date limiteTiempo, double porcentajeMinimo, boolean estado) {
        this.pruebaId = pruebaId;
        this.nombre = nombre;
        this.limiteTiempo = limiteTiempo;
        this.porcentajeMinimo = porcentajeMinimo;
        this.estado = estado;
    }

    public Long getPruebaId() {
        return pruebaId;
    }

    public void setPruebaId(Long pruebaId) {
        this.pruebaId = pruebaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getLimiteTiempo() {
        return limiteTiempo;
    }

    public void setLimiteTiempo(Date limiteTiempo) {
        this.limiteTiempo = limiteTiempo;
    }

    public double getPorcentajeMinimo() {
        return porcentajeMinimo;
    }

    public void setPorcentajeMinimo(double porcentajeMinimo) {
        this.porcentajeMinimo = porcentajeMinimo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Catalogo getArea() {
        return area;
    }

    public void setArea(Catalogo area) {
        this.area = area;
    }

    public List<Pregunta> getPreguntaList() {
        return preguntaList;
    }

    public void setPreguntaList(List<Pregunta> preguntaList) {
        this.preguntaList = preguntaList;
    }

    public List<PerfilHasPrueba> getPerfilHasPruebaList() {
        return perfilHasPruebaList;
    }

    public void setPerfilHasPruebaList(List<PerfilHasPrueba> perfilHasPruebaList) {
        this.perfilHasPruebaList = perfilHasPruebaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pruebaId != null ? pruebaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prueba)) {
            return false;
        }
        Prueba other = (Prueba) object;
        if ((this.pruebaId == null && other.pruebaId != null) || (this.pruebaId != null && !this.pruebaId.equals(other.pruebaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.Prueba[ pruebaId=" + pruebaId + " ]";
    }
    
}
