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
import javax.validation.constraints.Size;

/**
 *
 * @author BASP
 */
@Entity
@Table(name = "pregunta_plantilla")
@NamedQueries({
    @NamedQuery(name = "PreguntaPlantilla.findAll", query = "SELECT p FROM PreguntaPlantilla p")})
public class PreguntaPlantilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pregunta_id")
    private Long preguntaId;
    @Size(max = 2147483647)
    @Column(name = "enunciado")
    private String enunciado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntaPlantillaPreguntaId")
    private List<OpcionPlantilla> opcionPlantillaList;
    @JoinColumn(name = "prueba_plantilla_prueba_id", referencedColumnName = "prueba_id")
    @ManyToOne(optional = false)
    private PruebaPlantilla pruebaPlantillaPruebaId;

    public PreguntaPlantilla() {
    }

    public PreguntaPlantilla(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public List<OpcionPlantilla> getOpcionPlantillaList() {
        return opcionPlantillaList;
    }

    public void setOpcionPlantillaList(List<OpcionPlantilla> opcionPlantillaList) {
        this.opcionPlantillaList = opcionPlantillaList;
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
        hash += (preguntaId != null ? preguntaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PreguntaPlantilla)) {
            return false;
        }
        PreguntaPlantilla other = (PreguntaPlantilla) object;
        if ((this.preguntaId == null && other.preguntaId != null) || (this.preguntaId != null && !this.preguntaId.equals(other.preguntaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.PreguntaPlantilla[ preguntaId=" + preguntaId + " ]";
    }
    
}
