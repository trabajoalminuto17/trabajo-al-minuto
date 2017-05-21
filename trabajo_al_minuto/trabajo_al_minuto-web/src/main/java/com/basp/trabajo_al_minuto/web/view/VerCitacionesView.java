/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Citacion;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_OFERTA_PAGE;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_POSTULACION_PAGE;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class VerCitacionesView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Citacion> citacionesFiltradas;
    private List<Citacion> citaciones;
    private Citacion citacionSeleccionada;
    private Usuario usuarioLogin;

    @PostConstruct
    public void init() {
        try {
            usuarioLogin = getUserLogin();
            if (usuarioLogin.getRol().getRolId() == 3L) {
                citaciones = citacionEjb.getCitacionesActivasByUsuario(usuarioLogin.getUsuarioId());
            } else {
                citaciones = citacionEjb.getCitacionesActivasByEmpresa(usuarioLogin.getEmpresa().getEmpresaId());
            }
        } catch (BusinessException ex) {
            Logger.getLogger(VerCitacionesView.class.getName()).log(Level.SEVERE, ex.developerException());
        }

    }

    public void onRowSelectVerCitacion(SelectEvent event) {
        try {
            Usuario u = getUserLogin();
            FacesContext context = FacesContext.getCurrentInstance();
            Citacion c = (Citacion) event.getObject();
            context.getExternalContext().getSessionMap().put("ofertaId", c.getUsuarioHasOferta().getOfertasOfertaId().getOfertaId());
            if (u.getRol().getRolId() == 3L) {
                context.getExternalContext().redirect(DETALLE_OFERTA_PAGE);
            } else {
                context.getExternalContext().getSessionMap().put("usuarioHasOfertaId", c.getUsuarioHasOferta().getUsuarioHasOfertaId());
                context.getExternalContext().redirect(DETALLE_POSTULACION_PAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(VerCitacionesView.class.getName()).log(Level.SEVERE, "onRowSelectVerCitacion", ex);
        }
    }

//    @Getter and Setter
    public List<Citacion> getCitacionesFiltradas() {
        return citacionesFiltradas;
    }

    public void setCitacionesFiltradas(List<Citacion> citacionesFiltradas) {
        this.citacionesFiltradas = citacionesFiltradas;
    }

    public Citacion getCitacionSeleccionada() {
        return citacionSeleccionada;
    }

    public void setCitacionSeleccionada(Citacion citacionSeleccionada) {
        this.citacionSeleccionada = citacionSeleccionada;
    }

    public List<Citacion> getCitaciones() {
        return citaciones;
    }

    public void setCitaciones(List<Citacion> citaciones) {
        this.citaciones = citaciones;
    }

    public Usuario getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Usuario usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

}
