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

/**
 *
 * @author BASP
 */
@Entity
@Table(name = "usuario_has_oferta")
@NamedQueries({
    @NamedQuery(name = "UsuarioHasOferta.findAll", query = "SELECT u FROM UsuarioHasOferta u")})
public class UsuarioHasOferta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usuario_has_oferta_id")
    private Long usuarioHasOfertaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioHasOferta")
    private List<Citacion> citacionList;
    @JoinColumn(name = "estado", referencedColumnName = "catalogo_id")
    @ManyToOne(optional = false)
    private Catalogo estado;
    @JoinColumn(name = "ofertas_oferta_id", referencedColumnName = "oferta_id")
    @ManyToOne(optional = false)
    private Oferta ofertasOfertaId;
    @JoinColumn(name = "usuario_usuario_id", referencedColumnName = "usuario_id")
    @ManyToOne(optional = false)
    private Usuario usuarioUsuarioId;

    public UsuarioHasOferta() {
    }

    public UsuarioHasOferta(Long usuarioHasOfertaId) {
        this.usuarioHasOfertaId = usuarioHasOfertaId;
    }

    public Long getUsuarioHasOfertaId() {
        return usuarioHasOfertaId;
    }

    public void setUsuarioHasOfertaId(Long usuarioHasOfertaId) {
        this.usuarioHasOfertaId = usuarioHasOfertaId;
    }

    public List<Citacion> getCitacionList() {
        return citacionList;
    }

    public void setCitacionList(List<Citacion> citacionList) {
        this.citacionList = citacionList;
    }

    public Catalogo getEstado() {
        return estado;
    }

    public void setEstado(Catalogo estado) {
        this.estado = estado;
    }

    public Oferta getOfertasOfertaId() {
        return ofertasOfertaId;
    }

    public void setOfertasOfertaId(Oferta ofertasOfertaId) {
        this.ofertasOfertaId = ofertasOfertaId;
    }

    public Usuario getUsuarioUsuarioId() {
        return usuarioUsuarioId;
    }

    public void setUsuarioUsuarioId(Usuario usuarioUsuarioId) {
        this.usuarioUsuarioId = usuarioUsuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioHasOfertaId != null ? usuarioHasOfertaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioHasOferta)) {
            return false;
        }
        UsuarioHasOferta other = (UsuarioHasOferta) object;
        if ((this.usuarioHasOfertaId == null && other.usuarioHasOfertaId != null) || (this.usuarioHasOfertaId != null && !this.usuarioHasOfertaId.equals(other.usuarioHasOfertaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta[ usuarioHasOfertaId=" + usuarioHasOfertaId + " ]";
    }
    
}
