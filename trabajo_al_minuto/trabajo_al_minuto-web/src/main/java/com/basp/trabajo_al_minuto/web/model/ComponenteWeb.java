/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.model;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.ejb.AdminEjb;
import com.basp.trabajo_al_minuto.service.ejb.CitacionEjb;
import com.basp.trabajo_al_minuto.service.ejb.OfertaEjb;
import com.basp.trabajo_al_minuto.service.ejb.PerfilEjb;
import com.basp.trabajo_al_minuto.service.ejb.PruebaEjb;
import com.basp.trabajo_al_minuto.service.ejb.UsuarioEjb;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import com.basp.trabajo_al_minuto.service.entity.Prueba;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import java.io.Serializable;
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

    /**
     * Injectamos los EJB para poder acceder a las propiedades de cada una de
     * las clases*
     */
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
    @Inject
    protected AdminEjb adminEjb;

    protected FacesMessage message;

    /**
     * Captura la sesión del usuario *
     */
    protected Usuario getUserLogin() {
        Usuario userlogin = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUsuario");
        if (userlogin != null) {
            return userlogin;
        }
        System.out.println("Usuario de sesión es nulo");
        return null;
    }

    /**
     * Genera los menus de usuario *
     */
    protected MenuModel getMenus() {
        MenuModel menus = (MenuModel) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("menusUsuario");
        if (menus != null) {
            return menus;
        }
        return null;
    }

    /**
     * captura el id del usuario *
     */
    protected Long getUsuarioId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioId");
    }

    /**
     * captura el id de la oferta *
     */
    protected Long getOfertaId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ofertaId");
    }

    /**
     * captura el id de la citacion *
     */
    protected Long getCitacionId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("citacionId");
    }

    /**
     * Captura el id de la oferta que tiene asignado un usuario *
     */
    protected Long getUsuarioHasOfertaId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioHasOfertaId");
    }

    /**
     * captura el de una prueba *
     */
    protected Long getPruebaId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pruebaId");
    }

    /**
     * captura el id de la prueba planilla *
     */
    protected Long getPruebaPlantillaId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pruebaPlantillaId");
    }

    protected Boolean getPruebasOk() {
        return (Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pruebasOk");
    }

    /**
     * captura el id de las ofertas *
     */
    protected Long getOfertaExternalId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ofertaexternalId");
    }

    /**
     * captura el id de una evaluacion *
     */
    protected Long getEvaluacionId() {
        return (Long) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("evaluacionId");
    }

    /**
     * Retorna todas aquellas pruebas que han sido resueltas *
     */
    protected List<Prueba> getPreubasResueltas() {
        return (List<Prueba>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("preubasResueltasId");
    }

    /**
     * Retorna una lista de los catalogos hijos según el id del padre
     */
    protected List<Catalogo> getCatalogosByParent(Long id) throws BusinessException {
        return adminEjb.getCatalogosByParent(id);
    }
}
