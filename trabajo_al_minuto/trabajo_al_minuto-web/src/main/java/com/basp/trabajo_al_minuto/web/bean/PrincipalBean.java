/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.bean;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.ERROR_PAGE;
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

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class PrincipalBean extends ComponenteWeb implements Serializable {

    private Usuario usuarioSeleccionado;

    @PostConstruct
    public void init() {
        usuarioSeleccionado = getUSER_LOGIN();
        if (usuarioSeleccionado != null) {

        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(ERROR_PAGE);
            } catch (IOException ex) {
                Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, "IOException.init", ex);
            }
        }

    }

    public List<Usuario> getUsuariosActivos() {
        try {
            return usuarioEjb.getUsuariosByEmpresa(getUSER_LOGIN().getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

//    GETTER AND SETTER
    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

}
