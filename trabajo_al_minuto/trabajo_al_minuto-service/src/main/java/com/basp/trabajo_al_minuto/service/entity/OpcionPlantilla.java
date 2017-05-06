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
@Table(name = "opcion_plantilla")
@NamedQueries({
    @NamedQuery(name = "OpcionPlantilla.findAll", query = "SELECT o FROM OpcionPlantilla o")})
public class OpcionPlantilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "opcion_id")
    private Long opcionId;
    @Size(max = 2147483647)
    @Column(name = "enunciado")
    private String enunciado;
    @Column(name = "correcta")
    private Boolean correcta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opcionPlantillaOpcionId")
    private List<PreguntaPlantillaHasOpcionPlantilla> preguntaPlantillaHasOpcionPlantillaList;

    public OpcionPlantilla() {
    }

    public OpcionPlantilla(Long opcionId) {
        this.opcionId = opcionId;
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

    public Boolean getCorrecta() {
        return correcta;
    }

    public void setCorrecta(Boolean correcta) {
        this.correcta = correcta;
    }

    public List<PreguntaPlantillaHasOpcionPlantilla> getPreguntaPlantillaHasOpcionPlantillaList() {
        return preguntaPlantillaHasOpcionPlantillaList;
    }

    public void setPreguntaPlantillaHasOpcionPlantillaList(List<PreguntaPlantillaHasOpcionPlantilla> preguntaPlantillaHasOpcionPlantillaList) {
        this.preguntaPlantillaHasOpcionPlantillaList = preguntaPlantillaHasOpcionPlantillaList;
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
        if (!(object instanceof OpcionPlantilla)) {
            return false;
        }
        OpcionPlantilla other = (OpcionPlantilla) object;
        if ((this.opcionId == null && other.opcionId != null) || (this.opcionId != null && !this.opcionId.equals(other.opcionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.OpcionPlantilla[ opcionId=" + opcionId + " ]";
    }
    
}
