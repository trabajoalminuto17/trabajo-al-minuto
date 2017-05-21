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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author BASP
 */
@Entity
@Table(name = "oferta")
@NamedQueries({
    @NamedQuery(name = "Oferta.findAll", query = "SELECT o FROM Oferta o")})
public class Oferta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "oferta_id")
    private Long ofertaId;
    @Column(name = "fecha_expiracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpiracion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ofertasOfertaId")
    private List<UsuarioHasOferta> usuarioHasOfertaList;
    @JoinColumn(name = "perfil", referencedColumnName = "perfil_id")
    @ManyToOne(optional = false)
    private Perfil perfil;
    @JoinColumn(name = "usuario_autor", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario usuarioAutor;

    public Oferta() {
    }

    public Oferta(Long ofertaId) {
        this.ofertaId = ofertaId;
    }

    public Oferta(Long ofertaId, boolean estado) {
        this.ofertaId = ofertaId;
        this.estado = estado;
    }

    public Long getOfertaId() {
        return ofertaId;
    }

    public void setOfertaId(Long ofertaId) {
        this.ofertaId = ofertaId;
    }

    public Date getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(Date fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<UsuarioHasOferta> getUsuarioHasOfertaList() {
        return usuarioHasOfertaList;
    }

    public void setUsuarioHasOfertaList(List<UsuarioHasOferta> usuarioHasOfertaList) {
        this.usuarioHasOfertaList = usuarioHasOfertaList;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Usuario getUsuarioAutor() {
        return usuarioAutor;
    }

    public void setUsuarioAutor(Usuario usuarioAutor) {
        this.usuarioAutor = usuarioAutor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ofertaId != null ? ofertaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oferta)) {
            return false;
        }
        Oferta other = (Oferta) object;
        if ((this.ofertaId == null && other.ofertaId != null) || (this.ofertaId != null && !this.ofertaId.equals(other.ofertaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.Oferta[ ofertaId=" + ofertaId + " ]";
    }
    
}
