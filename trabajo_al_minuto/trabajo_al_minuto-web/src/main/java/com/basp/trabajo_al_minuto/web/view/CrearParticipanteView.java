/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.model.business.BusinessSecurity;
import com.basp.trabajo_al_minuto.service.entity.Candidato;
import com.basp.trabajo_al_minuto.service.entity.Persona;
import com.basp.trabajo_al_minuto.service.entity.Rol;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.USUARIO_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.USUARIO_OK;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.USUARIO_YA_EXISTE;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.webMessage;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Johnny
 */
@Named
@ViewScoped
public class CrearParticipanteView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario newUsuario;
    private Persona newPersona;
    private Candidato newCandidato;
    private Boolean render;
    private Boolean terminos;

    public CrearParticipanteView() {
        newUsuario = new Usuario();
        newPersona = new Persona();
        newCandidato = new Candidato();
    }

    @PostConstruct
    public void init() {
        render = Boolean.FALSE;
    }
    
    public void createParticipante() {
        try {
            newUsuario.setCambioClave(Boolean.FALSE);
            newUsuario.setEstado(Boolean.TRUE);
            newUsuario.setFechaCreacion(new Date());
            newUsuario.setPassword(BusinessSecurity.encrypt(newUsuario.getPassword()));
            newUsuario.setRol(new Rol(3L));
            newUsuario.setTerminos(Boolean.FALSE);
            newUsuario.setPersona(newPersona);
            newUsuario.setEmail(newUsuario.getEmail().toLowerCase());
            newUsuario.setCambioClave(Boolean.TRUE);
            newCandidato.setAspiracionSalarial(newCandidato.getAspiracionSalarial());
            newCandidato.setEscolaridad(newCandidato.getEscolaridad());
            newCandidato.setEstadoCandidato(Boolean.TRUE);
            newUsuario.setCandidato(newCandidato);
            usuarioEjb.updateUsuario(newUsuario);
            render = Boolean.TRUE;
            message = webMessage(USUARIO_OK);
        } catch (BusinessException ex) {
            message = webMessage(USUARIO_NOT);
            Logger.getLogger(CrearParticipanteView.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void validarEmail() {
        try {
            if (usuarioEjb.getUsuarioByEmail(newUsuario.getEmail().toLowerCase()) != null) {
                message = webMessage(USUARIO_YA_EXISTE);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (BusinessException ex) {
            Logger.getLogger(CrearParticipanteView.class.getName()).log(Level.SEVERE, ex.developerException());
        }
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

    public Candidato getNewCandidato() {
        return newCandidato;
    }

    public void setNewCandidato(Candidato newCandidato) {
        this.newCandidato = newCandidato;
    }

    public Boolean getTerminos() {
        return terminos;
    }

    public void setTerminos(Boolean terminos) {
        this.terminos = terminos;
    }

}
