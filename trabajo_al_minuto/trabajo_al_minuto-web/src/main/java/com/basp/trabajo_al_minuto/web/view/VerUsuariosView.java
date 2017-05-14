/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_USUARIO_PAGE;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class VerUsuariosView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuarioSeleccionado;
    private List<Usuario> usuariosFlitrados;

    public List<Usuario> getUsuariosByEmpresa() {
        try {
            Usuario u = getUserLogin();
            List<Usuario> r = usuarioEjb.getUsuariosByEmpresa(u.getEmpresa().getEmpresaId());
            r.remove(u);
            return r;
        } catch (BusinessException ex) {
            Logger.getLogger(VerUsuariosView.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public void onRowSelectVerUsuarios(SelectEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioId", ((Usuario) event.getObject()).getUsuarioId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_USUARIO_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(VerUsuariosView.class.getName()).log(Level.SEVERE, "onRowSelectViewChannels", ex);
        }
    }
//    @Getter and Setter

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
}
