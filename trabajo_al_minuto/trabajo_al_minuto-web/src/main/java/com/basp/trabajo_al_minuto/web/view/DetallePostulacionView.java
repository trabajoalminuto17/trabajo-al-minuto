/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_OK;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_OK;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_RECHAZADA_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_RECHAZADA_OK;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.descargarArchivoPdf;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.webMessage;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import com.basp.trabajo_al_minuto.service.entity.Citacion;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author BASP
 */
@Named
@ViewScoped
public class DetallePostulacionView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private Oferta ofertaSeleccionada;
    private Citacion citacionSeleccionada;
    private UsuarioHasOferta usuarioHasOfertaSeleccionada;
    private StreamedContent streamedContent;
    private Citacion newCitacion;

    public DetallePostulacionView() {
        newCitacion = new Citacion();
    }

    @PostConstruct
    public void init() {
        try {
            if (getOfertaId() != null) {
                ofertaSeleccionada = ofertaEjb.findOferta(getOfertaId());
            }
            if (getUsuarioHasOfertaId() != null) {
                usuarioHasOfertaSeleccionada = usuarioEjb.findUsuarioHasOferta(getUsuarioHasOfertaId());
                citacionSeleccionada = usuarioHasOfertaSeleccionada.getCitacion();
            }
        } catch (BusinessException ex) {
            Logger.getLogger(DetallePostulacionView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void descargarHojaDeVida(String url) {
        streamedContent = descargarArchivoPdf(url);
    }

    public void rechazarCitacion() {
        try {
            usuarioHasOfertaSeleccionada.setEstado(new Catalogo(9L));
            usuarioHasOfertaSeleccionada = usuarioEjb.updateUsuarioHasOferta(usuarioHasOfertaSeleccionada);
            message = webMessage(CITACION_RECHAZADA_OK);
        } catch (BusinessException ex) {
            message = webMessage(CITACION_RECHAZADA_NOT);
            Logger.getLogger(DetallePostulacionView.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void createCitacion() {
        try {
            Usuario u = getUserLogin();
            usuarioHasOfertaSeleccionada.setEstado(new Catalogo(11L));
            newCitacion.setActivarPruebas(Boolean.FALSE);
            newCitacion.setFechaCreacion(new Date());
            newCitacion.setResuelto(Boolean.FALSE);
            newCitacion.setEstado(Boolean.TRUE);
            newCitacion.setUsuarioAutor(u);
            newCitacion.setUsuarioHasOferta(usuarioHasOfertaSeleccionada);
            citacionSeleccionada = citacionEjb.updateCitacion(newCitacion);
            usuarioHasOfertaSeleccionada = usuarioEjb.updateUsuarioHasOferta(usuarioHasOfertaSeleccionada);
            message = webMessage(CITACION_OK);
        } catch (BusinessException ex) {
            message = webMessage(CITACION_NOT);
            Logger.getLogger(DetallePostulacionView.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void updateCitacion() {
        try {
            citacionSeleccionada = citacionEjb.updateCitacion(citacionSeleccionada);
            webMessage(CHANGE_OK);
        } catch (BusinessException ex) {
            webMessage(CHANGE_NOT);
            Logger.getLogger(DetallePostulacionView.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

//    @Getter and Setter
    public Oferta getOfertaSeleccionada() {
        return ofertaSeleccionada;
    }

    public void setOfertaSeleccionada(Oferta ofertaSeleccionada) {
        this.ofertaSeleccionada = ofertaSeleccionada;
    }

    public UsuarioHasOferta getUsuarioHasOfertaSeleccionada() {
        return usuarioHasOfertaSeleccionada;
    }

    public void setUsuarioHasOfertaSeleccionada(UsuarioHasOferta usuarioHasOfertaSeleccionada) {
        this.usuarioHasOfertaSeleccionada = usuarioHasOfertaSeleccionada;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }

    public Citacion getNewCitacion() {
        return newCitacion;
    }

    public void setNewCitacion(Citacion newCitacion) {
        this.newCitacion = newCitacion;
    }

    public Citacion getCitacionSeleccionada() {
        return citacionSeleccionada;
    }

    public void setCitacionSeleccionada(Citacion citacionSeleccionada) {
        this.citacionSeleccionada = citacionSeleccionada;
    }

}
