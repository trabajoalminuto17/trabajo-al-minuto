/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.bean;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.model.business.BusinessSecurity;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.ERROR_PAGE;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.INICIO_PAGE;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.NO_PASSWORD;
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
public class AdminBean extends ComponenteWeb implements Serializable {

    private String clave;
    private Usuario usuarioSession;

    @PostConstruct
    public void init() {
        usuarioSession = getUSER_LOGIN();
        this.sessionArriba();
    }

    public void cambiarClave() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Usuario u = getUSER_LOGIN();
            u.setPassword(BusinessSecurity.encrypt(clave));
            u.setCambioClave(Boolean.TRUE);
            context.getExternalContext().getSessionMap().put("sessionUsuario", usuarioEjb.updateUsuario(u));
            context.getExternalContext().redirect(INICIO_PAGE);
        } catch (BusinessException ex) {
            webMessage(NO_PASSWORD);
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, ex.developerException());
        } catch (IOException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, "IOException.cambiarClave", ex);
        } finally {
            if (message != null) {
                context.addMessage(null, message);
            }
        }
    }

    public void sessionArriba() {
        try {
            if (usuarioSession == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(ERROR_PAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, "sessionArriba", ex);
        }
    }

    public void claveArriba() {
        try {
            if (usuarioSession.getCambioClave()) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(INICIO_PAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(AdminBean.class.getName()).log(Level.SEVERE, "claveArriba", ex);
        }
    }

//    GETTER AND SETTER
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }
}
