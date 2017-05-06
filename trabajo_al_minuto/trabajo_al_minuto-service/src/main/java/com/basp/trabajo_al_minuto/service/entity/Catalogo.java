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
@Table(name = "catalogo")
@NamedQueries({
    @NamedQuery(name = "Catalogo.findAll", query = "SELECT c FROM Catalogo c")})
public class Catalogo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "catalogo_id")
    private Long catalogoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "valor")
    private String valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "orden")
    private int orden;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private List<Prueba> pruebaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private List<PruebaPlantilla> pruebaPlantillaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private List<UsuarioHasOferta> usuarioHasOfertaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private List<Perfil> perfilList;
    @OneToMany(mappedBy = "catalogoPadre")
    private List<Catalogo> catalogoList;
    @JoinColumn(name = "catalogo_padre", referencedColumnName = "catalogo_id")
    @ManyToOne
    private Catalogo catalogoPadre;

    public Catalogo() {
    }

    public Catalogo(Long catalogoId) {
        this.catalogoId = catalogoId;
    }

    public Catalogo(Long catalogoId, String nombre, String valor, boolean estado, int orden) {
        this.catalogoId = catalogoId;
        this.nombre = nombre;
        this.valor = valor;
        this.estado = estado;
        this.orden = orden;
    }

    public Long getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(Long catalogoId) {
        this.catalogoId = catalogoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public List<Prueba> getPruebaList() {
        return pruebaList;
    }

    public void setPruebaList(List<Prueba> pruebaList) {
        this.pruebaList = pruebaList;
    }

    public List<PruebaPlantilla> getPruebaPlantillaList() {
        return pruebaPlantillaList;
    }

    public void setPruebaPlantillaList(List<PruebaPlantilla> pruebaPlantillaList) {
        this.pruebaPlantillaList = pruebaPlantillaList;
    }

    public List<UsuarioHasOferta> getUsuarioHasOfertaList() {
        return usuarioHasOfertaList;
    }

    public void setUsuarioHasOfertaList(List<UsuarioHasOferta> usuarioHasOfertaList) {
        this.usuarioHasOfertaList = usuarioHasOfertaList;
    }

    public List<Perfil> getPerfilList() {
        return perfilList;
    }

    public void setPerfilList(List<Perfil> perfilList) {
        this.perfilList = perfilList;
    }

    public List<Catalogo> getCatalogoList() {
        return catalogoList;
    }

    public void setCatalogoList(List<Catalogo> catalogoList) {
        this.catalogoList = catalogoList;
    }

    public Catalogo getCatalogoPadre() {
        return catalogoPadre;
    }

    public void setCatalogoPadre(Catalogo catalogoPadre) {
        this.catalogoPadre = catalogoPadre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (catalogoId != null ? catalogoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Catalogo)) {
            return false;
        }
        Catalogo other = (Catalogo) object;
        if ((this.catalogoId == null && other.catalogoId != null) || (this.catalogoId != null && !this.catalogoId.equals(other.catalogoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.Catalogo[ catalogoId=" + catalogoId + " ]";
    }
    
}
