/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.model.business.BusinessAttributes.HOST_EMAIL_SERVER;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.ACCESO_DENEGADO;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CLAVE_RESTAURADA_OK;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CLAVE_RESTAURAD_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.USUARIO_NO_ENCONTRADO;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.webMessage;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.model.business.BusinessHtmlTemplates;
import com.basp.trabajo_al_minuto.model.business.BusinessSecurity;
import static com.basp.trabajo_al_minuto.model.business.BusinessUtils.sendEmail;
import com.basp.trabajo_al_minuto.model.dto.EmailMessage;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class RestaurarView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private String usuario;
    private Boolean ok;

    public void restaurarClave() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Usuario u = usuarioEjb.getUsuarioByEmail(usuario.toLowerCase());
            if (u != null) {
                if (u.getEstado()) {
                    String nueva_clave = RandomStringUtils.random(12, true, true);
                    if (enviarClaveRestaurada(u.getEmail(), u.getPersona().getNombre(), nueva_clave)) {
                        u.setCambioClave(Boolean.FALSE);
                        u.setPassword(BusinessSecurity.encrypt(nueva_clave));
                        usuarioEjb.updateUsuario(u);
                        message = webMessage(CLAVE_RESTAURADA_OK);
                        ok = Boolean.TRUE;
                    } else {
                        message = webMessage(CLAVE_RESTAURAD_NOT);
                    }
                } else {
                    message = webMessage(ACCESO_DENEGADO);
                }
            } else {
                message = webMessage(USUARIO_NO_ENCONTRADO);
            }
        } catch (BusinessException ex) {
            message = webMessage(USUARIO_NO_ENCONTRADO);
            Logger.getLogger(RestaurarView.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            if (message != null) {
                context.addMessage(null, message);
            }
        }
    }

    private Boolean enviarClaveRestaurada(String email, String nombre, String clave) throws BusinessException {

        List<String> lisTo = new ArrayList();
        lisTo.add(email);

        EmailMessage em = new EmailMessage();
        em.setFrom("trabajoalminuto@gmail.com");
        em.setUser("trabajoalminuto@gmail.com");
        em.setPassword("tam12345");
        em.setPort(587);
        em.setStarttls(Boolean.TRUE);
        em.setMask("Trabajo al minuto");
        em.setSubject("Contraseña restaurada!");
        em.setBodyMessage(BusinessHtmlTemplates.restourarClaveTemplate(clave, nombre, email));
        em.setToList(lisTo);
        em.setHost(HOST_EMAIL_SERVER);
        em.setMimeTypeMessage("text/html; charset=utf-8");
        return sendEmail(em);
    }

    public void ok_off() {
        ok = Boolean.FALSE;
    }

    //    @Getter and Setter
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

}
