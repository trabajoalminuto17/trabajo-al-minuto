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
@Table(name = "prueba_plantilla_has_pregunta_plantilla")
@NamedQueries({
    @NamedQuery(name = "PruebaPlantillaHasPreguntaPlantilla.findAll", query = "SELECT p FROM PruebaPlantillaHasPreguntaPlantilla p")})
public class PruebaPlantillaHasPreguntaPlantilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prueba_has_pregunta_id")
    private Long pruebaHasPreguntaId;
    @JoinColumn(name = "pregunta_plantilla_pregunta_id", referencedColumnName = "pregunta_id")
    @ManyToOne(optional = false)
    private PreguntaPlantilla preguntaPlantillaPreguntaId;
    @JoinColumn(name = "prueba_plantilla_prueba_id", referencedColumnName = "prueba_id")
    @ManyToOne(optional = false)
    private PruebaPlantilla pruebaPlantillaPruebaId;

    public PruebaPlantillaHasPreguntaPlantilla() {
    }

    public PruebaPlantillaHasPreguntaPlantilla(Long pruebaHasPreguntaId) {
        this.pruebaHasPreguntaId = pruebaHasPreguntaId;
    }

    public Long getPruebaHasPreguntaId() {
        return pruebaHasPreguntaId;
    }

    public void setPruebaHasPreguntaId(Long pruebaHasPreguntaId) {
        this.pruebaHasPreguntaId = pruebaHasPreguntaId;
    }

    public PreguntaPlantilla getPreguntaPlantillaPreguntaId() {
        return preguntaPlantillaPreguntaId;
    }

    public void setPreguntaPlantillaPreguntaId(PreguntaPlantilla preguntaPlantillaPreguntaId) {
        this.preguntaPlantillaPreguntaId = preguntaPlantillaPreguntaId;
    }

    public PruebaPlantilla getPruebaPlantillaPruebaId() {
        return pruebaPlantillaPruebaId;
    }

    public void setPruebaPlantillaPruebaId(PruebaPlantilla pruebaPlantillaPruebaId) {
        this.pruebaPlantillaPruebaId = pruebaPlantillaPruebaId;
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
        if (!(object instanceof PruebaPlantillaHasPreguntaPlantilla)) {
            return false;
        }
        PruebaPlantillaHasPreguntaPlantilla other = (PruebaPlantillaHasPreguntaPlantilla) object;
        if ((this.pruebaHasPreguntaId == null && other.pruebaHasPreguntaId != null) || (this.pruebaHasPreguntaId != null && !this.pruebaHasPreguntaId.equals(other.pruebaHasPreguntaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.PruebaPlantillaHasPreguntaPlantilla[ pruebaHasPreguntaId=" + pruebaHasPreguntaId + " ]";
    }
    
}
