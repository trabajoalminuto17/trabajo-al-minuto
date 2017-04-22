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
@Table(name = "opcion")
@NamedQueries({
    @NamedQuery(name = "Opcion.findAll", query = "SELECT o FROM Opcion o")})
public class Opcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "opcion_id")
    private Long opcionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "enunciado")
    private String enunciado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "correcta")
    private boolean correcta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcion")
    private List<PreguntaHasOpcion> preguntaHasOpcionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcion")
    private List<Respuesta> respuestaList;

    public Opcion() {
    }

    public Opcion(Long opcionId) {
        this.opcionId = opcionId;
    }

    public Opcion(Long opcionId, String enunciado, boolean correcta) {
        this.opcionId = opcionId;
        this.enunciado = enunciado;
        this.correcta = correcta;
    }

    public Long getOpcionId() {
        return opcionId;
    }

    public void setOpcionId(Long opcionId) {
        this.opcionId = opcionId;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

    public List<PreguntaHasOpcion> getPreguntaHasOpcionList() {
        return preguntaHasOpcionList;
    }

    public void setPreguntaHasOpcionList(List<PreguntaHasOpcion> preguntaHasOpcionList) {
        this.preguntaHasOpcionList = preguntaHasOpcionList;
    }

    public List<Respuesta> getRespuestaList() {
        return respuestaList;
    }

    public void setRespuestaList(List<Respuesta> respuestaList) {
        this.respuestaList = respuestaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opcionId != null ? opcionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opcion)) {
            return false;
        }
        Opcion other = (Opcion) object;
        if ((this.opcionId == null && other.opcionId != null) || (this.opcionId != null && !this.opcionId.equals(other.opcionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.Opcion[ opcionId=" + opcionId + " ]";
    }
    
}
