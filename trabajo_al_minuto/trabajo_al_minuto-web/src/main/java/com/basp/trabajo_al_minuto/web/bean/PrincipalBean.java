/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.bean;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.dte.OfertaAplicada;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Persona;
import com.basp.trabajo_al_minuto.service.entity.Rol;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.ERROR_PAGE;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_OK;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.USUARIO_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.USUARIO_OK;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
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
public class PrincipalBean extends ComponenteWeb implements Serializable {

    private Usuario usuarioSeleccionado;
    private Usuario usuarioSession;
    private List<Usuario> usuariosFlitrados;

    private Usuario newUsuario;
    private Persona newPersona;

    private Boolean render;

    public PrincipalBean() {
        newUsuario = new Usuario();
        newPersona = new Persona();
        render = Boolean.FALSE;
    }

    @PostConstruct
    public void init() {
        usuarioSession = getUSER_LOGIN();
        if (usuarioSession != null) {
            try {
                if (usuarioSession.getRol().getRolId() == 1 || usuarioSession.getRol().getRolId() == 4) {
                    this.validateUsuarioSession();
                }
            } catch (BusinessException ex) {
                Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(ERROR_PAGE);
            } catch (IOException ex) {
                Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, "IOException.init", ex);
            }
        }

    }

    private void validateUsuarioSession() throws BusinessException {
        if (getUsuarioId() != null) {
            usuarioSeleccionado = usuarioEjb.findUsuario(getUsuarioId());
        }
    }

    public List<Usuario> getUsuariosByEmpresa() {
        try {
            return usuarioEjb.getUsuariosByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public List<OfertaAplicada> getOfertasMasAplicadasByEmpresa() {
        try {
            return ofertaEjb.getOfertasMasAplicadasByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public List<Evaluacion> getUsuariosMejoresResultados() {
        try {
            return usuarioEjb.getUsuariosMejoresResultadosByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public void onRowSelectVerUsuarios(SelectEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioId", ((Usuario) event.getObject()).getUsuarioId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_USUARIO_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, "onRowSelectViewChannels", ex);
        }
    }

    public void updateUsuario() {
        try {
            usuarioSeleccionado = usuarioEjb.updateUsuario(usuarioSeleccionado);
            webMessage(CHANGE_OK);
        } catch (BusinessException ex) {
            webMessage(CHANGE_NOT);
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void createUsuario() {
        try {
            newUsuario.setCambioClave(Boolean.FALSE);
            newUsuario.setEmpresa(getUSER_LOGIN().getEmpresa());
            newUsuario.setEstado(Boolean.TRUE);
            newUsuario.setFechaCreacion(new Date());
            newUsuario.setPassword(newUsuario.getEmail());
            newUsuario.setRol(new Rol(2L));
            newUsuario.setTerminos(Boolean.FALSE);
            newUsuario.setPersona(newPersona);
            usuarioEjb.updateUsuario(newUsuario);
            render = Boolean.TRUE;
            webMessage(USUARIO_OK);
        } catch (BusinessException ex) {
            webMessage(USUARIO_NOT);
            Logger.getLogger(PrincipalBean.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

//    GETTER AND SETTER
    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<Usuario> getUsuariosFlitrados() {
        return usuariosFlitrados;
    }

    public void setUsuariosFlitrados(List<Usuario> usuariosFlitrados) {
        this.usuariosFlitrados = usuariosFlitrados;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public Usuario getNewUsuario() {
        return newUsuario;
    }

    public void setNewUsuario(Usuario newUsuario) {
        this.newUsuario = newUsuario;
    }

    public Persona getNewPersona() {
        return newPersona;
    }

    public void setNewPersona(Persona newPersona) {
        this.newPersona = newPersona;
    }

    public Boolean getRender() {
        return render;
    }

    public void setRender(Boolean render) {
        this.render = render;
    }

}
