/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.INICIO_PAGE;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.NO_PASSWORD;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.webMessage;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.model.business.BusinessSecurity;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
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
public class ClaveView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private String clave;
    private Boolean terminos;

    @PostConstruct
    public void init() {
        terminos = getUserLogin().getTerminos();
    }

    public void claveArriba() {
        Usuario u = getUserLogin();
        try {
            if (u.getCambioClave()) {
                FacesContext.getCurrentInstance().getExternalContext().redirect(INICIO_PAGE);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClaveView.class.getName()).log(Level.SEVERE, "claveArriba", ex);
        }
    }

    public void cambiarClave() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Usuario u = getUserLogin();
            u.setTerminos(terminos);
            u.setPassword(BusinessSecurity.encrypt(clave));
            u.setCambioClave(Boolean.TRUE);
            context.getExternalContext().getSessionMap().put("sessionUsuario", usuarioEjb.updateUsuario(u));
            context.getExternalContext().redirect(INICIO_PAGE);
        } catch (BusinessException ex) {
            message = webMessage(NO_PASSWORD);
            Logger.getLogger(ClaveView.class.getName()).log(Level.SEVERE, ex.developerException());
        } catch (IOException ex) {
            Logger.getLogger(ClaveView.class.getName()).log(Level.SEVERE, "IOException.cambiarClave", ex);
        } finally {
            if (message != null) {
                context.addMessage(null, message);
            }
        }
    }

    //    @Getter and Setter
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Boolean getTerminos() {
        return terminos;
    }

    public void setTerminos(Boolean terminos) {
        this.terminos = terminos;
    }

}
