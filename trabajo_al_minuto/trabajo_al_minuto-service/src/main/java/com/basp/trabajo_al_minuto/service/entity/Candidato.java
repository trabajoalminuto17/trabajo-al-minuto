/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "candidato")
@NamedQueries({
    @NamedQuery(name = "Candidato.findAll", query = "SELECT c FROM Candidato c")})
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "candidato_id")
    private Long candidatoId;
    @Size(max = 2147483647)
    @Column(name = "aspiracion_salarial")
    private String aspiracionSalarial;
    @Size(max = 2147483647)
    @Column(name = "ruta_hoja_de_vida")
    private String rutaHojaDeVida;
    @Size(max = 2147483647)
    @Column(name = "escolaridad")
    private String escolaridad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_candidato")
    private boolean estadoCandidato;
    @OneToMany(mappedBy = "candidato")
    private List<Usuario> usuarioList;

    public Candidato() {
    }

    public Candidato(Long candidatoId) {
        this.candidatoId = candidatoId;
    }

    public Candidato(Long candidatoId, boolean estadoCandidato) {
        this.candidatoId = candidatoId;
        this.estadoCandidato = estadoCandidato;
    }

    public Long getCandidatoId() {
        return candidatoId;
    }

    public void setCandidatoId(Long candidatoId) {
        this.candidatoId = candidatoId;
    }

    public String getAspiracionSalarial() {
        return aspiracionSalarial;
    }

    public void setAspiracionSalarial(String aspiracionSalarial) {
        this.aspiracionSalarial = aspiracionSalarial;
    }

    public String getRutaHojaDeVida() {
        return rutaHojaDeVida;
    }

    public void setRutaHojaDeVida(String rutaHojaDeVida) {
        this.rutaHojaDeVida = rutaHojaDeVida;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public boolean getEstadoCandidato() {
        return estadoCandidato;
    }

    public void setEstadoCandidato(boolean estadoCandidato) {
        this.estadoCandidato = estadoCandidato;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (candidatoId != null ? candidatoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Candidato)) {
            return false;
        }
        Candidato other = (Candidato) object;
        if ((this.candidatoId == null && other.candidatoId != null) || (this.candidatoId != null && !this.candidatoId.equals(other.candidatoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.Candidato[ candidatoId=" + candidatoId + " ]";
    }
    
}
