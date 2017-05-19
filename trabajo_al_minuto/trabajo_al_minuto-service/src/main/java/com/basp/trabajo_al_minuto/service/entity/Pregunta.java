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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author BASP
 */
@Entity
@Table(name = "pregunta")
@NamedQueries({
    @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p")})
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pregunta_id")
    private Long preguntaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "enunciado")
    private String enunciado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntaPreguntaId")
    private List<Opcion> opcionList;
    @JoinColumn(name = "prueba_prueba_id", referencedColumnName = "prueba_id")
    @ManyToOne(optional = false)
    private Prueba pruebaPruebaId;

    public Pregunta() {
    }

    public Pregunta(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public Pregunta(Long preguntaId, String enunciado) {
        this.preguntaId = preguntaId;
        this.enunciado = enunciado;
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

    public List<Opcion> getOpcionList() {
        return opcionList;
    }

    public void setOpcionList(List<Opcion> opcionList) {
        this.opcionList = opcionList;
    }

    public Prueba getPruebaPruebaId() {
        return pruebaPruebaId;
    }

    public void setPruebaPruebaId(Prueba pruebaPruebaId) {
        this.pruebaPruebaId = pruebaPruebaId;
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
        if (!(object instanceof Pregunta)) {
            return false;
        }
        Pregunta other = (Pregunta) object;
        if ((this.preguntaId == null && other.preguntaId != null) || (this.preguntaId != null && !this.preguntaId.equals(other.preguntaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.Pregunta[ preguntaId=" + preguntaId + " ]";
    }
    
}
