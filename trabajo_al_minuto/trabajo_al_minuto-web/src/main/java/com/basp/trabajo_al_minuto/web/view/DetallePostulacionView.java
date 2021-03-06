/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.model.business.BusinessAttributes.HOST_EMAIL_SERVER;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_OK;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_OK;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_RECHAZADA_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_RECHAZADA_OK;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.descargarArchivoPdf;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.webMessage;

import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.model.business.BusinessHtmlTemplates;
import static com.basp.trabajo_al_minuto.model.business.BusinessUtils.sendEmail;
import com.basp.trabajo_al_minuto.model.dto.EmailMessage;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import com.basp.trabajo_al_minuto.service.entity.Citacion;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.PRUEBA_ACTIVADA;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.PRUEBA_NO_ACTIVADA;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private Date fechaActual;

    /**
     * Constructor*
     */
    public DetallePostulacionView() {
        newCitacion = new Citacion();
    }

    /**
     * Codigo a ejecutar una vez se ha instanciado la clase
     */
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
            fechaActual = new Date();
        } catch (BusinessException ex) {
            Logger.getLogger(DetallePostulacionView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permite descargar un archivo PDF*
     */
    public void descargarHojaDeVida(String url) {
        streamedContent = descargarArchivoPdf(url);
    }

    /**
     * Cambia el estado de la citacion a rechazado y actualiza la información*
     */
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

    /**
     * Metodo que captura la informacion diligenciada por el participante cada
     * vez que va a crear una citación y la inserta en la tabla citacion
     */
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
            citacionSeleccionada = citacionEjb.updateCitacion(newCitacion);//Actualiza la informacion de la citacion
            usuarioHasOfertaSeleccionada = usuarioEjb.updateUsuarioHasOferta(usuarioHasOfertaSeleccionada);
            enviarCitacion(newCitacion, usuarioHasOfertaSeleccionada);//Envia el email con los datos de participante
            message = webMessage(CITACION_OK);
        } catch (BusinessException ex) {
            message = webMessage(CITACION_NOT);
            Logger.getLogger(DetallePostulacionView.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * Actualiza la informacion de la citacion *
     */
    public void updateCitacion() {
        try {
            citacionSeleccionada = citacionEjb.updateCitacion(citacionSeleccionada);
            actualizarCitacion(citacionSeleccionada, usuarioHasOfertaSeleccionada);
            message = webMessage(CHANGE_OK);
        } catch (BusinessException ex) {
            message = webMessage(CHANGE_NOT);
            Logger.getLogger(DetallePostulacionView.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * Cambia el estado de FALSE a TRUE de la columna activarPrueba
     */
    public void activarPrueba() {
        try {
            if (citacionEjb.activarPrueba(citacionSeleccionada.getCitacionId()) == 1) {//Envía el id de la citacion seleccionada. Si el resultado de la actualizacion es 1 quiere decir que hubo exito en el proceso
                message = webMessage(PRUEBA_ACTIVADA);
            } else {
                message = webMessage(PRUEBA_NO_ACTIVADA);
            }
        } catch (BusinessException ex) {
            message = webMessage(PRUEBA_NO_ACTIVADA);
            Logger.getLogger(DetallePostulacionView.class.getName()).log(Level.SEVERE, ex.developerException());
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    /**
     * Envía todos los datos necesarios para generar y enviar el correo al
     * usuario cada vez que se crea una citacion *
     */
    public Boolean enviarCitacion(Citacion citacion, UsuarioHasOferta hasOferta) throws BusinessException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");//formatea la fecha de citación que será mostrada al usuario
        String fechaCitacion = df.format(citacion.getFechaCitacion());
        List<String> lisTo = new ArrayList();
        lisTo.add(hasOferta.getUsuarioUsuarioId().getEmail());
        EmailMessage em = new EmailMessage();
        em.setFrom("trabajoalminuto@gmail.com");//email de origen
        em.setUser("trabajoalminuto@gmail.com");//usuario que será mostrado
        em.setPassword("tam12345");//contraseña de la cuenta de correo
        em.setPort(587);//puerto del servidor de correo
        em.setStarttls(Boolean.TRUE);
        em.setMask("Trabajo al minuto");//Etiqueta de presentacion del correo
        em.setSubject("Proceso selección!");//Asunto del correo
        em.setBodyMessage(BusinessHtmlTemplates.enviarCitacion(hasOferta.getOfertasOfertaId().getUsuarioAutor().getEmpresa().getNombre(), fechaCitacion, hasOferta.getUsuarioUsuarioId().getPersona().getNombre(), citacion.getLugar(), citacion.getDetalles(), hasOferta.getOfertasOfertaId().getPerfil().getTitulo()));//parametros que se envian a la plantilla para genear el cuerpo del correo
        em.setToList(lisTo);
        em.setHost(HOST_EMAIL_SERVER);//Host del servidor de correo
        em.setMimeTypeMessage("text/html; charset=utf-8");//
        return sendEmail(em);
    }

    /**
     * Envía todos los datos necesarios para generar y enviar el correo al
     * usuario cada vez que se modifique los datos de una citacion *
     */
    public Boolean actualizarCitacion(Citacion citacion, UsuarioHasOferta hasOferta) throws BusinessException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");//formatea la fecha de citación que será mostrada al usuario
        String fechaCitacion = df.format(citacion.getFechaCitacion());
        List<String> lisTo = new ArrayList();
        lisTo.add(hasOferta.getUsuarioUsuarioId().getEmail());
        EmailMessage em = new EmailMessage();
        em.setFrom("trabajoalminuto@gmail.com");//email de origen
        em.setUser("trabajoalminuto@gmail.com");//usuario que será mostrado
        em.setPassword("tam12345");//contraseña de la cuenta de correo
        em.setPort(587);
        em.setStarttls(Boolean.TRUE);
        em.setMask("Trabajo al minuto");
        em.setSubject("Citación reprogramada!");
        em.setBodyMessage(BusinessHtmlTemplates.actualizarCitacion(hasOferta.getOfertasOfertaId().getUsuarioAutor().getEmpresa().getNombre(), fechaCitacion, hasOferta.getUsuarioUsuarioId().getPersona().getNombre(), citacion.getLugar(), citacion.getDetalles()));//parametros que se envian a la plantilla para genear el cuerpo del correo
        em.setToList(lisTo);
        em.setHost(HOST_EMAIL_SERVER);
        em.setMimeTypeMessage("text/html; charset=utf-8");
        return sendEmail(em);
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

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

}
