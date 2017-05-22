/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_PERFIL_PAGE;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.PORTAL_PAGE;

import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.PERFIL_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.SALIR_NOT;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.webMessage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class TopbarView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private String usuarioNombre;
    private String usuarioEmpresa;

    @PostConstruct
    public void init() {
        usuarioNombre = getUserLogin().getPersona().getNombre();
        if (getUserLogin().getRol().getRolId() != 3L) {
            usuarioEmpresa = getUserLogin().getEmpresa().getNombre();
        } else {
            if (getPruebasOk()) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("menusUsuario", null);
            }
        }
    }

    public void destruirSesion() {
        try {
            if (!getPruebasOk()) {
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                FacesContext.getCurrentInstance().getExternalContext().redirect(PORTAL_PAGE);
            } else {
                message = webMessage(SALIR_NOT);
            }
        } catch (IOException ex) {
            Logger.getLogger(TopbarView.class.getName()).log(Level.SEVERE, "destruirSesion", ex);
        } finally {
            if (message != null) {
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void renderPerfil() {
        try {
            if (!getPruebasOk()) {
                FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
                FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_PERFIL_PAGE);
            } else {
                message = webMessage(PERFIL_NOT);
            }
        } catch (IOException ex) {
            Logger.getLogger(TopbarView.class.getName()).log(Level.SEVERE, "renderPerfil", ex);
        } finally {
            if (message != null) {
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

//    @Getter and Setter
    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioEmpresa() {
        return usuarioEmpresa;
    }

    public void setUsuarioEmpresa(String usuarioEmpresa) {
        this.usuarioEmpresa = usuarioEmpresa;
    }

}
