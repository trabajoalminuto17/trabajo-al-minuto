/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.service.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author BASP
 */
@Entity
@Table(name = "citacion")
@NamedQueries({
    @NamedQuery(name = "Citacion.findAll", query = "SELECT c FROM Citacion c")})
public class Citacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "citacion_id")
    private Long citacionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_citacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCitacion;
    @Size(max = 2147483647)
    @Column(name = "lugar")
    private String lugar;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 2147483647)
    @Column(name = "detalles")
    private String detalles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resuelto")
    private boolean resuelto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activar_pruebas")
    private boolean activarPruebas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "usuario_autor", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario usuarioAutor;
    @JoinColumn(name = "usuario_has_oferta", referencedColumnName = "usuario_has_oferta_id")
    @OneToOne(optional = false)
    private UsuarioHasOferta usuarioHasOferta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "citacion")
    private List<Evaluacion> evaluacionList;

    public Citacion() {
    }

    public Citacion(Long citacionId) {
        this.citacionId = citacionId;
    }

    public Citacion(Long citacionId, Date fechaCitacion, boolean resuelto, boolean activarPruebas, boolean estado) {
        this.citacionId = citacionId;
        this.fechaCitacion = fechaCitacion;
        this.resuelto = resuelto;
        this.activarPruebas = activarPruebas;
        this.estado = estado;
    }

    public Long getCitacionId() {
        return citacionId;
    }

    public void setCitacionId(Long citacionId) {
        this.citacionId = citacionId;
    }

    public Date getFechaCitacion() {
        return fechaCitacion;
    }

    public void setFechaCitacion(Date fechaCitacion) {
        this.fechaCitacion = fechaCitacion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public boolean getResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public boolean getActivarPruebas() {
        return activarPruebas;
    }

    public void setActivarPruebas(boolean activarPruebas) {
        this.activarPruebas = activarPruebas;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Usuario getUsuarioAutor() {
        return usuarioAutor;
    }

    public void setUsuarioAutor(Usuario usuarioAutor) {
        this.usuarioAutor = usuarioAutor;
    }

    public UsuarioHasOferta getUsuarioHasOferta() {
        return usuarioHasOferta;
    }

    public void setUsuarioHasOferta(UsuarioHasOferta usuarioHasOferta) {
        this.usuarioHasOferta = usuarioHasOferta;
    }

    public List<Evaluacion> getEvaluacionList() {
        return evaluacionList;
    }

    public void setEvaluacionList(List<Evaluacion> evaluacionList) {
        this.evaluacionList = evaluacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (citacionId != null ? citacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citacion)) {
            return false;
        }
        Citacion other = (Citacion) object;
        if ((this.citacionId == null && other.citacionId != null) || (this.citacionId != null && !this.citacionId.equals(other.citacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.Citacion[ citacionId=" + citacionId + " ]";
    }
    
}
