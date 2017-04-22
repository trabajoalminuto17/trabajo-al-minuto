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
import javax.validation.constraints.NotNull;

/**
 *
 * @author BASP
 */
@Entity
@Table(name = "rol_has_menu")
@NamedQueries({
    @NamedQuery(name = "RolHasMenu.findAll", query = "SELECT r FROM RolHasMenu r")})
public class RolHasMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rol_has_menu_id")
    private Integer rolHasMenuId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @JoinColumn(name = "menu", referencedColumnName = "menu_id")
    @ManyToOne(optional = false)
    private Menu menu;
    @JoinColumn(name = "rol", referencedColumnName = "rol_id")
    @ManyToOne(optional = false)
    private Rol rol;

    public RolHasMenu() {
    }

    public RolHasMenu(Integer rolHasMenuId) {
        this.rolHasMenuId = rolHasMenuId;
    }

    public RolHasMenu(Integer rolHasMenuId, boolean estado) {
        this.rolHasMenuId = rolHasMenuId;
        this.estado = estado;
    }

    public Integer getRolHasMenuId() {
        return rolHasMenuId;
    }

    public void setRolHasMenuId(Integer rolHasMenuId) {
        this.rolHasMenuId = rolHasMenuId;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolHasMenuId != null ? rolHasMenuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolHasMenu)) {
            return false;
        }
        RolHasMenu other = (RolHasMenu) object;
        if ((this.rolHasMenuId == null && other.rolHasMenuId != null) || (this.rolHasMenuId != null && !this.rolHasMenuId.equals(other.rolHasMenuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.basp.trabajo_al_minuto.service.entity.RolHasMenu[ rolHasMenuId=" + rolHasMenuId + " ]";
    }
    
}
