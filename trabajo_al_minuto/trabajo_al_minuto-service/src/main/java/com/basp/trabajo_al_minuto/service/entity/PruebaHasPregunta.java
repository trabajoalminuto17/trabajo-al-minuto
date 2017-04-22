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
@Table(name = "prueba_has_pregunta")
@NamedQueries({
    @NamedQuery(name = "PruebaHasPregunta.findAll", query = "SELECT p FROM PruebaHasPregunta p")})
public class PruebaHasPregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prueba_has_pregunta_id")
    private Long pruebaHasPreguntaId;
    @JoinColumn(name = "pregunta", referencedColumnName = "pregunta_id")
    @ManyToOne(optional = false)
    private Pregunta pregunta;
    @JoinColumn(name = "prueba", referencedColumnName = "prueba_id")
    @ManyToOne(optional = false)
    private Prueba prueba;

    public PruebaHasPregunta() {
    }

    public PruebaHasPregunta(Long pruebaHasPreguntaId) {
        this.pruebaHasPreguntaId = pruebaHasPreguntaId;
    }

    public Long getPruebaHasPreguntaId() {
        return pruebaHasPreguntaId;
    }

    public void setPruebaHasPreguntaId(Long pruebaHasPreguntaId) {
        this.pruebaHasPreguntaId = pruebaHasPreguntaId;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
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
        hash += (pruebaHasPreguntaId != null ? pruebaHasPreguntaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaHasPregunta)) {
            return false;
        }
        PruebaHasPregunta other = (PruebaHasPregunta) object;
        if ((this.pruebaHasPreguntaId == null && other.pruebaHasPreguntaId != null) || (this.pruebaHasPreguntaId != null && !this.pruebaHasPreguntaId.equals(other.pruebaHasPreguntaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.PruebaHasPregunta[ pruebaHasPreguntaId=" + pruebaHasPreguntaId + " ]";
    }
    
}
