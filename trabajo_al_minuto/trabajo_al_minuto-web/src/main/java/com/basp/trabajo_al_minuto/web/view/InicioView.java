/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.model.business.BusinessAttributes.HOST_EMAIL_SERVER;
import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.model.business.BusinessHtmlTemplates;
import static com.basp.trabajo_al_minuto.model.business.BusinessUtils.sendEmail;
import com.basp.trabajo_al_minuto.model.dto.EmailMessage;
import com.basp.trabajo_al_minuto.service.dte.OfertaAplicada;
import com.basp.trabajo_al_minuto.service.entity.Citacion;
import com.basp.trabajo_al_minuto.service.entity.Evaluacion;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_EVALUACION_PAGE;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_OFERTA_PAGE;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.INICIO_PAGE;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.PRUEBA_PLANTILLA_PAGE;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CHANGE_OK;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.formatDate;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.webMessage;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
public class InicioView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuarioSession;
    private List<OfertaAplicada> ofertasPopulares;
    private List<Evaluacion> usuariosMejoresResultados;
    private List<Citacion> proximasCitaciones;
    private Boolean pruebas_ok;
    private Citacion citacion;

    @PostConstruct
    public void init() {
        try {
            usuarioSession = getUserLogin();
            pruebas_ok = Boolean.FALSE;
            if (usuarioSession.getRol().getRolId() == 3L) {
                List<Citacion> response = citacionEjb.getCitacionesActivasByUsuario(usuarioSession.getUsuarioId());
                if (!response.isEmpty()) {
                    for (Citacion c : response) {
                        if (c.getActivarPruebas()) {
                            citacion = c;
                            pruebas_ok = Boolean.TRUE;
                            break;
                        }
                    }
                    if (!pruebas_ok) {
                        if (response.size() <= 6) {
                            proximasCitaciones = response;
                        } else {
                            proximasCitaciones = response.subList(0, 5);
                        }
                    }
                }
                if (!pruebas_ok) {
                    ofertasPopulares = ofertaEjb.getOfertasMasAplicadas();
                }
            } else {
                ofertasPopulares = ofertaEjb.getOfertasMasAplicadasByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
                usuariosMejoresResultados = usuarioEjb.getUsuariosMejoresResultadosByEmpresa(usuarioSession.getEmpresa().getEmpresaId());
            }
        } catch (BusinessException ex) {
            Logger.getLogger(InicioView.class.getName()).log(Level.SEVERE, ex.developerException());
        }
    }

    public void goDetalleOferta(Oferta o) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ofertaId", o.getOfertaId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_OFERTA_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(InicioView.class.getName()).log(Level.SEVERE, "onRowSelectVerOfertas", ex);
        }
    }

    public String getFormatDate(Date date) {
        return formatDate(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

    public void rechazarPruebas() {
        try {
            pruebas_ok = Boolean.FALSE;
            citacion.setActivarPruebas(pruebas_ok);
            citacion = citacionEjb.updateCitacion(citacion);
            citacionRechazada(citacion);
            FacesContext.getCurrentInstance().getExternalContext().redirect(INICIO_PAGE);
        } catch (BusinessException ex) {
            Logger.getLogger(InicioView.class.getName()).log(Level.SEVERE, ex.developerException());
        } catch (IOException ex) {
            Logger.getLogger(InicioView.class.getName()).log(Level.SEVERE, "rechazarPruebas", ex);
        }
    }

    public void aceptarPruebas() {
        try {
            usuarioSession.getCandidato().setPruebasActivas(Boolean.TRUE);
            usuarioSession = usuarioEjb.updateUsuario(usuarioSession);
            Evaluacion e = new Evaluacion();
            e.setCitacion(citacion);
            e.setEstado(Boolean.TRUE);
            e.setPorcentaje(0D);
            e.setTiempoEmpleado(new Date());
            e = pruebaEjb.updateEvaluacion(e);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("evaluacionId", e.getEvaluacionId());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("citacionId", citacion.getCitacionId());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ofertaId", citacion.getUsuarioHasOferta().getOfertasOfertaId().getOfertaId());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionUsuario", usuarioSession);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pruebasOk", Boolean.TRUE);
            FacesContext.getCurrentInstance().getExternalContext().redirect(PRUEBA_PLANTILLA_PAGE);
        } catch (BusinessException ex) {
            Logger.getLogger(InicioView.class.getName()).log(Level.SEVERE, ex.developerException());
        } catch (IOException ex) {
            Logger.getLogger(InicioView.class.getName()).log(Level.SEVERE, "aceptarPruebas", ex);
        }
    }

    private Boolean citacionRechazada(Citacion citacion) throws BusinessException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fechaCitacion = df.format(citacion.getFechaCitacion());
        List<String> lisTo = new ArrayList();
        lisTo.add(citacion.getUsuarioAutor().getEmail());
        EmailMessage em = new EmailMessage();
        em.setFrom("trabajoalminuto@gmail.com");
        em.setUser("trabajoalminuto@gmail.com");
        em.setPassword("tam12345");
        em.setPort(587);
        em.setStarttls(Boolean.TRUE);
        em.setMask("Trabajo al minuto");
        em.setSubject("Citación rechazada!");
        em.setBodyMessage(BusinessHtmlTemplates.citacionRechazada(
                citacion.getUsuarioHasOferta().getOfertasOfertaId().getPerfil().getTitulo(),
                fechaCitacion, citacion.getUsuarioHasOferta().getUsuarioUsuarioId().getPersona().
                getNombre(), citacion.getLugar(), citacion.getDetalles()));
        em.setToList(lisTo);
        em.setHost(HOST_EMAIL_SERVER);
        em.setMimeTypeMessage("text/html; charset=utf-8");
        return sendEmail(em);
    }

    //    @Getter and Setter
    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public List<OfertaAplicada> getOfertasPopulares() {
        return ofertasPopulares;
    }

    public void setOfertasPopulares(List<OfertaAplicada> ofertasPopulares) {
        this.ofertasPopulares = ofertasPopulares;
    }

    public List<Evaluacion> getUsuariosMejoresResultados() {
        return usuariosMejoresResultados;
    }

    public void setUsuariosMejoresResultados(List<Evaluacion> usuariosMejoresResultados) {
        this.usuariosMejoresResultados = usuariosMejoresResultados;
    }

    public List<Citacion> getProximasCitaciones() {
        return proximasCitaciones;
    }

    public void setProximasCitaciones(List<Citacion> proximasCitaciones) {
        this.proximasCitaciones = proximasCitaciones;
    }

    public Boolean getPruebas_ok() {
        return pruebas_ok;
    }

    public void setPruebas_ok(Boolean pruebas_ok) {
        this.pruebas_ok = pruebas_ok;
    }

}
