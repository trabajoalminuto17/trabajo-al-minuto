/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.INICIAR_SESSION_PAGE;

import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
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
        usuarioEmpresa = getUserLogin().getEmpresa().getNombre();
    }

    public void destruirSesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect(INICIAR_SESSION_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(TopbarView.class.getName()).log(Level.SEVERE, "destruirSesion", ex);
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
