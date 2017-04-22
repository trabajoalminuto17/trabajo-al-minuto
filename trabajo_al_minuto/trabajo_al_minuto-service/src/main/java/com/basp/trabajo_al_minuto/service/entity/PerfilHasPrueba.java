/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author BASP
 */
@Entity
@Table(name = "perfil_has_prueba")
@NamedQueries({
    @NamedQuery(name = "PerfilHasPrueba.findAll", query = "SELECT p FROM PerfilHasPrueba p")})
public class PerfilHasPrueba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "perfil_has_prueba_id")
    private Integer perfilHasPruebaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "porcentaje")
    private double porcentaje;
    @JoinColumn(name = "perfil", referencedColumnName = "perfil_id")
    @ManyToOne(optional = false)
    private Perfil perfil;
    @JoinColumn(name = "prueba", referencedColumnName = "prueba_id")
    @ManyToOne(optional = false)
    private Prueba prueba;

    public PerfilHasPrueba() {
    }

    public PerfilHasPrueba(Integer perfilHasPruebaId) {
        this.perfilHasPruebaId = perfilHasPruebaId;
    }

    public PerfilHasPrueba(Integer perfilHasPruebaId, double porcentaje) {
        this.perfilHasPruebaId = perfilHasPruebaId;
        this.porcentaje = porcentaje;
    }

    public Integer getPerfilHasPruebaId() {
        return perfilHasPruebaId;
    }

    public void setPerfilHasPruebaId(Integer perfilHasPruebaId) {
        this.perfilHasPruebaId = perfilHasPruebaId;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Prueba getPrueba() {
        return prueba;
    }

    public void setPrueba(Prueba prueba) {
        this.prueba = prueba;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perfilHasPruebaId != null ? perfilHasPruebaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PerfilHasPrueba)) {
            return false;
        }
        PerfilHasPrueba other = (PerfilHasPrueba) object;
        if ((this.perfilHasPruebaId == null && other.perfilHasPruebaId != null) || (this.perfilHasPruebaId != null && !this.perfilHasPruebaId.equals(other.perfilHasPruebaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.PerfilHasPrueba[ perfilHasPruebaId=" + perfilHasPruebaId + " ]";
    }
    
}
