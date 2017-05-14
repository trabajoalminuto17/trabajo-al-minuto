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
import javax.validation.constraints.Size;

/**
 *
 * @author BASP
 */
@Entity
@Table(name = "prueba_plantilla")
@NamedQueries({
    @NamedQuery(name = "PruebaPlantilla.findAll", query = "SELECT p FROM PruebaPlantilla p")})
public class PruebaPlantilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prueba_id")
    private Long pruebaId;
    @Size(max = 2147483647)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "limite_tiempo")
    @Temporal(TemporalType.TIME)
    private Date limiteTiempo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentaje_minimo")
    private Double porcentajeMinimo;
    @Column(name = "estado")
    private Boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pruebaPlantillaPruebaId")
    private List<PruebaPlantillaHasPreguntaPlantilla> pruebaPlantillaHasPreguntaPlantillaList;
    @JoinColumn(name = "area", referencedColumnName = "catalogo_id")
    @ManyToOne(optional = false)
    private Catalogo area;
    @JoinColumn(name = "empresa_empresa_id", referencedColumnName = "empresa_id")
    @ManyToOne(optional = false)
    private Empresa empresaEmpresaId;

    public PruebaPlantilla() {
    }

    public PruebaPlantilla(Long pruebaId) {
        this.pruebaId = pruebaId;
    }

    public Long getPruebaId() {
        return pruebaId;
    }

    public void setPruebaId(Long pruebaId) {
        this.pruebaId = pruebaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getLimiteTiempo() {
        return limiteTiempo;
    }

    public void setLimiteTiempo(Date limiteTiempo) {
        this.limiteTiempo = limiteTiempo;
    }

    public Double getPorcentajeMinimo() {
        return porcentajeMinimo;
    }

    public void setPorcentajeMinimo(Double porcentajeMinimo) {
        this.porcentajeMinimo = porcentajeMinimo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<PruebaPlantillaHasPreguntaPlantilla> getPruebaPlantillaHasPreguntaPlantillaList() {
        return pruebaPlantillaHasPreguntaPlantillaList;
    }

    public void setPruebaPlantillaHasPreguntaPlantillaList(List<PruebaPlantillaHasPreguntaPlantilla> pruebaPlantillaHasPreguntaPlantillaList) {
        this.pruebaPlantillaHasPreguntaPlantillaList = pruebaPlantillaHasPreguntaPlantillaList;
    }

    public Catalogo getArea() {
        return area;
    }

    public void setArea(Catalogo area) {
        this.area = area;
    }

    public Empresa getEmpresaEmpresaId() {
        return empresaEmpresaId;
    }

    public void setEmpresaEmpresaId(Empresa empresaEmpresaId) {
        this.empresaEmpresaId = empresaEmpresaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pruebaId != null ? pruebaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PruebaPlantilla)) {
            return false;
        }
        PruebaPlantilla other = (PruebaPlantilla) object;
        if ((this.pruebaId == null && other.pruebaId != null) || (this.pruebaId != null && !this.pruebaId.equals(other.pruebaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.PruebaPlantilla[ pruebaId=" + pruebaId + " ]";
    }

}
