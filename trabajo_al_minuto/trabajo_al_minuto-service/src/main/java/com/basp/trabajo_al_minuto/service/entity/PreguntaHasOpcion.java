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

/**
 *
 * @author BASP
 */
@Entity
@Table(name = "pregunta_has_opcion")
@NamedQueries({
    @NamedQuery(name = "PreguntaHasOpcion.findAll", query = "SELECT p FROM PreguntaHasOpcion p")})
public class PreguntaHasOpcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pregunta_has_opcion_id")
    private Long preguntaHasOpcionId;
    @JoinColumn(name = "opcion", referencedColumnName = "opcion_id")
    @ManyToOne(optional = false)
    private Opcion opcion;
    @JoinColumn(name = "pregunta", referencedColumnName = "pregunta_id")
    @ManyToOne(optional = false)
    private Pregunta pregunta;

    public PreguntaHasOpcion() {
    }

    public PreguntaHasOpcion(Long preguntaHasOpcionId) {
        this.preguntaHasOpcionId = preguntaHasOpcionId;
    }

    public Long getPreguntaHasOpcionId() {
        return preguntaHasOpcionId;
    }

    public void setPreguntaHasOpcionId(Long preguntaHasOpcionId) {
        this.preguntaHasOpcionId = preguntaHasOpcionId;
    }

    public Opcion getOpcion() {
        return opcion;
    }

    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preguntaHasOpcionId != null ? preguntaHasOpcionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaHasOpcion)) {
            return false;
        }
        PreguntaHasOpcion other = (PreguntaHasOpcion) object;
        if ((this.preguntaHasOpcionId == null && other.preguntaHasOpcionId != null) || (this.preguntaHasOpcionId != null && !this.preguntaHasOpcionId.equals(other.preguntaHasOpcionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.PreguntaHasOpcion[ preguntaHasOpcionId=" + preguntaHasOpcionId + " ]";
    }
    
}
