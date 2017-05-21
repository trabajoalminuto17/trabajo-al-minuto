/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.CLAVE_PAGE;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.ACCESO_DENEGADO;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CREDENCIALES_INCORRECTAS;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.USUARIO_NO_ENCONTRADO;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.webMessage;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.model.business.BusinessSecurity;
import com.basp.trabajo_al_minuto.service.entity.Menu;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.INICIO_PAGE;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class InicioSesionView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private String usuario;
    private String clave;

    public void iniciarSesion() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
        try {
            Usuario session_usuario = usuarioEjb.getUsuarioByEmail(usuario.toLowerCase());
            if (session_usuario != null) {
                if (session_usuario.getEstado()) {
                    if (session_usuario.getCambioClave()) {
                        context.getExternalContext().getSessionMap().put("sessionUsuario", session_usuario);
                        context.getExternalContext().getSessionMap().put("menusUsuario", getMenuInicio(session_usuario.getRol().getRolId()));
                        context.getExternalContext().getSessionMap().put("userAgent", context.getExternalContext().getRequestHeaderMap().get("User-Agent"));
                        if (session_usuario.getPassword().equals(BusinessSecurity.encrypt(clave))) {
                            context.getExternalContext().redirect(INICIO_PAGE);
                        } else {
                            message = webMessage(CREDENCIALES_INCORRECTAS);
                        }
                    } else {
                        if (session_usuario.getPassword().equals(session_usuario.getEmail()) && session_usuario.getPassword().equals(clave)
                                || session_usuario.getPassword().equals(BusinessSecurity.encrypt(clave))) {
                            context.getExternalContext().getSessionMap().put("sessionUsuario", session_usuario);
                            context.getExternalContext().getSessionMap().put("menusUsuario", getMenuInicio(session_usuario.getRol().getRolId()));
                            context.getExternalContext().getSessionMap().put("userAgent", context.getExternalContext().getRequestHeaderMap().get("User-Agent"));
                            context.getExternalContext().redirect(CLAVE_PAGE);
                        } else {
                            message = webMessage(CREDENCIALES_INCORRECTAS);
                        }
                    }
                } else {
                    message = webMessage(ACCESO_DENEGADO);
                }
            } else {
                message = webMessage(USUARIO_NO_ENCONTRADO);
            }
        } catch (BusinessException ex) {
            message = webMessage(USUARIO_NO_ENCONTRADO);
            Logger.getLogger(InicioSesionView.class.getName()).log(Level.SEVERE, ex.developerException());
        } catch (java.lang.IllegalStateException ex) {
        } catch (IOException ex) {
            Logger.getLogger(InicioSesionView.class.getName()).log(Level.SEVERE, "iniciarSesion", ex);
        } finally {
            if (message != null) {
                context.addMessage(null, message);
            }
        }
    }

    private MenuModel getMenuInicio(Long rolId) {
        DefaultSeparator separator = new DefaultSeparator();
        separator.setStyleClass("fa fa-ellipsis-v menu-separator");
        try {
            MenuModel model = new DefaultMenuModel();
            List<Menu> response = usuarioEjb.getMenusByRol(rolId);
            for (int i = 0; i < response.size(); i++) {
                DefaultSubMenu menu = new DefaultSubMenu(response.get(i).getMenuPadre().getNombre());
                menu.setIcon(response.get(i).getMenuPadre().getIcono());
                for (int j = 0; j < response.size(); j++) {
                    if (response.get(i).getMenuPadre().getMenuId().equals(response.get(j).getMenuPadre().getMenuId())) {
                        DefaultMenuItem item = new DefaultMenuItem(response.get(j).getNombre());
                        item.setCommand(response.get(j).getUrl());
                        item.setIcon(response.get(j).getIcono());
                        item.setImmediate(Boolean.TRUE);
                        menu.addElement(item);
                    }
                }
                if (i > 0 && i <= response.size()) {
                    if (!response.get(i).getMenuPadre().getNombre().equals(response.get(i - 1).getMenuPadre().getNombre())) {
                        if (i > 0) {
                            model.addElement(separator);
                        }
                        model.addElement(menu);
                    }
                } else if (i < 1) {
                    model.addElement(menu);
                }
            }
            return model;
        } catch (BusinessException ex) {
            Logger.getLogger(InicioSesionView.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

//    @Getter and Setter
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

}
