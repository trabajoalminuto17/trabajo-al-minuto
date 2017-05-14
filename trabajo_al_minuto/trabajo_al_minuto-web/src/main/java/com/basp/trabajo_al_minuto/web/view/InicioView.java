/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.dte.OfertaAplicada;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class InicioView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuarioSession;

    @PostConstruct
    public void init() {
        usuarioSession = getUserLogin();
    }

    public List<OfertaAplicada> getOfertasMasAplicadasByEmpresa() {
        try {
            return ofertaEjb.getOfertasMasAplicadasByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(InicioView.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public List<Evaluacion> getUsuariosMejoresResultados() {
        try {
            return usuarioEjb.getUsuariosMejoresResultadosByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
        } catch (BusinessException ex) {
            Logger.getLogger(InicioView.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

//    @Getter and Setter
    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

}
