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
@Table(name = "pregunta_plantilla_has_opcion_plantilla")
@NamedQueries({
    @NamedQuery(name = "PreguntaPlantillaHasOpcionPlantilla.findAll", query = "SELECT p FROM PreguntaPlantillaHasOpcionPlantilla p")})
public class PreguntaPlantillaHasOpcionPlantilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pregunta_has_opcion_plantilla_id")
    private Long preguntaHasOpcionPlantillaId;
    @JoinColumn(name = "opcion_plantilla_opcion_id", referencedColumnName = "opcion_id")
    @ManyToOne(optional = false)
    private OpcionPlantilla opcionPlantillaOpcionId;
    @JoinColumn(name = "pregunta_plantilla_pregunta_id", referencedColumnName = "pregunta_id")
    @ManyToOne(optional = false)
    private PreguntaPlantilla preguntaPlantillaPreguntaId;

    public PreguntaPlantillaHasOpcionPlantilla() {
    }

    public PreguntaPlantillaHasOpcionPlantilla(Long preguntaHasOpcionPlantillaId) {
        this.preguntaHasOpcionPlantillaId = preguntaHasOpcionPlantillaId;
    }

    public Long getPreguntaHasOpcionPlantillaId() {
        return preguntaHasOpcionPlantillaId;
    }

    public void setPreguntaHasOpcionPlantillaId(Long preguntaHasOpcionPlantillaId) {
        this.preguntaHasOpcionPlantillaId = preguntaHasOpcionPlantillaId;
    }

    public OpcionPlantilla getOpcionPlantillaOpcionId() {
        return opcionPlantillaOpcionId;
    }

    public void setOpcionPlantillaOpcionId(OpcionPlantilla opcionPlantillaOpcionId) {
        this.opcionPlantillaOpcionId = opcionPlantillaOpcionId;
    }

    public PreguntaPlantilla getPreguntaPlantillaPreguntaId() {
        return preguntaPlantillaPreguntaId;
    }

    public void setPreguntaPlantillaPreguntaId(PreguntaPlantilla preguntaPlantillaPreguntaId) {
        this.preguntaPlantillaPreguntaId = preguntaPlantillaPreguntaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (preguntaHasOpcionPlantillaId != null ? preguntaHasOpcionPlantillaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaPlantillaHasOpcionPlantilla)) {
            return false;
        }
        PreguntaPlantillaHasOpcionPlantilla other = (PreguntaPlantillaHasOpcionPlantilla) object;
        if ((this.preguntaHasOpcionPlantillaId == null && other.preguntaHasOpcionPlantillaId != null) || (this.preguntaHasOpcionPlantillaId != null && !this.preguntaHasOpcionPlantillaId.equals(other.preguntaHasOpcionPlantillaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.PreguntaPlantillaHasOpcionPlantilla[ preguntaHasOpcionPlantillaId=" + preguntaHasOpcionPlantillaId + " ]";
    }
    
}
