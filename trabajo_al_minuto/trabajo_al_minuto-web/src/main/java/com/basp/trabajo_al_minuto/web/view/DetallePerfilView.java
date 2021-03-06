/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.RUTA_HOJA_DE_VIDA_PDF;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import com.basp.trabajo_al_minuto.web.model.MensajeWeb;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_OK;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.webMessage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.apache.commons.io.FileUtils;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Johnny
 */
@Named
@ViewScoped
public class DetallePerfilView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuarioLogueado;

    /**
     * Codigo a ejecutar una vez se ha instanciado la clase
     */
    @PostConstruct
    public void init() {
        usuarioLogueado = getUserLogin();
    }

    /**
     * Actualiza los datos del usuario*
     */
    public void updateUsuario() {
        try {
            usuarioLogueado = usuarioEjb.updateUsuario(usuarioLogueado);
            message = webMessage(CHANGE_OK);
        } catch (BusinessException ex) {
            message = webMessage(CHANGE_NOT);
            Logger.getLogger(DetalleUsuarioView.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * Copia el PDF seleccionado por el usuario*
     */
    public Boolean cargarPDF(FileUploadEvent event) {
        try {
            String fileName = usuarioLogueado.getPersona().getDocumento().concat("_HV.pdf");//Nombre del archivo que será guardado
            File file = new File(RUTA_HOJA_DE_VIDA_PDF + fileName);//Ruta donde será guardado el archivo
            FileUtils.copyInputStreamToFile(event.getFile().getInputstream(), file);//copia el archivo en la ruta indicada
            usuarioLogueado.getCandidato().setRutaHojaDeVida(file.getAbsolutePath());//inserta la descripcion de la ruta donde se guarda el archivo
            usuarioLogueado = usuarioEjb.updateUsuario(usuarioLogueado);//Actualiza la informacion del usuario
            message = webMessage(MensajeWeb.ARCHIVO_OK);
            return true;
        } catch (IOException ex) {
            message = webMessage(MensajeWeb.ARCHIVO_ERROR);
            Logger.getLogger(CrearParticipanteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BusinessException ex) {
            message = webMessage(MensajeWeb.ARCHIVO_ERROR);
            Logger.getLogger(DetallePerfilView.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return false;
    }

//    @Getter and Setter
    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

}
