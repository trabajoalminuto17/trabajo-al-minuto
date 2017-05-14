/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.model;

import static com.basp.trabajo_al_minuto.model.business.BusinessAttributes.HOST_EMAIL_SERVER;
import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.model.business.BusinessHtmlTemplates;
import static com.basp.trabajo_al_minuto.model.business.BusinessUtils.sendEmail;
import com.basp.trabajo_al_minuto.model.dto.EmailMessage;
import com.basp.trabajo_al_minuto.service.ejb.CitacionEjb;
import com.basp.trabajo_al_minuto.service.ejb.OfertaEjb;
import com.basp.trabajo_al_minuto.service.ejb.PerfilEjb;
import com.basp.trabajo_al_minuto.service.ejb.PruebaEjb;
import com.basp.trabajo_al_minuto.service.ejb.UsuarioEjb;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author BASP
 */
@Model
public class ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    protected OfertaEjb ofertaEjb;
    @Inject
    protected UsuarioEjb usuarioEjb;
    @Inject
    protected CitacionEjb citacionEjb;
    @Inject
    protected PerfilEjb perfilEjb;
    @Inject
    protected PruebaEjb pruebaEjb;

    protected FacesMessage message;

    protected Usuario getUserLogin() {
        Usuario userlogin = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUsuario");
        if (userlogin != null) {
            return userlogin;
        }
        System.out.println("Usuario de sesión es nulo");
        return null;
    }

    protected MenuModel getMenus() {
        MenuModel menus = (MenuModel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menusUsuario");
        if (menus != null) {
            return menus;
        }
        System.out.println("Menus de usuario es nulo");
        return null;
    }

    protected Long getUsuarioId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioId");
    }

    protected Long getOfertaId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ofertaId");
    }

    protected Long getUsuarioHasOfertaId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioHasOfertaId");
    }

    protected Long getPruebaId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pruebaId");
    }
}
