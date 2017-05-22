/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.basp.trabajo_al_minuto.web.view;

import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_OFERTA_PAGE;
import static com.basp.trabajo_al_minuto.web.model.AtributosWeb.DETALLE_POSTULACION_PAGE;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.CITACION_RECHAZADA;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.webMessage;

import com.basp.trabajo_al_minuto.service.entity.UsuarioHasOferta;
import com.basp.trabajo_al_minuto.model.business.BusinessException;
import com.basp.trabajo_al_minuto.service.entity.Catalogo;
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Prueba;
import com.basp.trabajo_al_minuto.service.entity.Usuario;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import com.basp.trabajo_al_minuto.web.model.MensajeWeb;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.OFERTA_APLICADA_NOT;
import static com.basp.trabajo_al_minuto.web.model.MensajeWeb.OFERTA_APLICADA_OK;
import static com.basp.trabajo_al_minuto.web.model.UtilWeb.formatDate;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
public class DetalleOfertaView extends ComponenteWeb implements Serializable {

    private static final long serialVersionUID = 1L;

    private Oferta ofertaSeleccionada;
    private UsuarioHasOferta usuarioHasOfertaSeleccionada;
    private Prueba preubaSeleccionada;
    private List<UsuarioHasOferta> usuarioHasOfertaFlitradas;
    private List<Prueba> pruebasFlitradas;
    private Usuario usuariologin;
    private UsuarioHasOferta ofertAplicada;

    @PostConstruct
    public void init() {
        try {
            usuariologin = getUserLogin();
            if (getOfertaId() != null) {
                ofertaSeleccionada = ofertaEjb.findOferta(getOfertaId());
            }
            if (usuariologin.getRol().getRolId() == 3L) {
                ofertAplicada = usuarioEjb.getUsuarioHasOfertaByUsuarioAndOferta(usuariologin.getUsuarioId(), ofertaSeleccionada.getOfertaId());
            } else {
                if (getUsuarioHasOfertaId() != null) {
                    usuarioHasOfertaSeleccionada = usuarioEjb.findUsuarioHasOferta(getUsuarioHasOfertaId());
                }
                if (getPruebaId() != null) {
                    preubaSeleccionada = pruebaEjb.findPrueba(getPruebaId());
                }
            }
        } catch (BusinessException ex) {
            Logger.getLogger(DetalleOfertaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<UsuarioHasOferta> getUsuarioHasOfertaByOferta() {
        try {
            return usuarioEjb.getUsuariosByOferta(ofertaSeleccionada.getOfertaId());
        } catch (BusinessException ex) {
            Logger.getLogger(DetalleOfertaView.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public void onRowSelectVerUsuarioHasOfertas(SelectEvent event) {
        try {
            UsuarioHasOferta uho = (UsuarioHasOferta) event.getObject();
            if (!uho.getCitacion().getActivarPruebas()) {
                if (uho.getEstado().getCatalogoId() != 9L) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioHasOfertaId", ((UsuarioHasOferta) event.getObject()).getUsuarioHasOfertaId());
                    FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_POSTULACION_PAGE);
                } else {
                    message = webMessage(CITACION_RECHAZADA);
                }
            } else {
                message = webMessage(MensajeWeb.USUARIO_EN_PRUEBAS);
            }
        } catch (IOException ex) {
            Logger.getLogger(DetalleOfertaView.class.getName()).log(Level.SEVERE, "onRowSelectVerUsuarioHasOfertas", ex);
        } finally {
            if (message != null) {
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public List<Prueba> getPruebasByPerfil() {
        try {
            return pruebaEjb.getPruebasByPerfil(ofertaSeleccionada.getPerfil().getPerfilId());
        } catch (BusinessException ex) {
            Logger.getLogger(DetalleOfertaView.class.getName()).log(Level.SEVERE, ex.developerException());
            return null;
        }
    }

    public void onRowSelectVerPruebas(SelectEvent event) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pruebaId", ((Prueba) event.getObject()).getPruebaId());
            FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_OFERTA_PAGE);
        } catch (IOException ex) {
            Logger.getLogger(DetalleOfertaView.class.getName()).log(Level.SEVERE, "onRowSelectVerPruebas", ex);
        }
    }

    public void aplicarOferta() {
        try {
            UsuarioHasOferta uho = new UsuarioHasOferta();
            uho.setEstado(new Catalogo(8L));
            uho.setFechaPostulacion(new Date());
            uho.setOfertasOfertaId(ofertaSeleccionada);
            uho.setUsuarioUsuarioId(usuariologin);
            ofertAplicada = usuarioEjb.updateUsuarioHasOferta(uho);
            message = webMessage(OFERTA_APLICADA_OK);
        } catch (BusinessException ex) {
            Logger.getLogger(DetalleOfertaView.class.getName()).log(Level.SEVERE, null, ex);
            message = webMessage(OFERTA_APLICADA_NOT);
        } finally {
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String getFormatDate(Date date) {
        return formatDate(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
    }

//    @Getter and Setter
    public Oferta getOfertaSeleccionada() {
        return ofertaSeleccionada;
    }

    public void setOfertaSeleccionada(Oferta ofertaSeleccionada) {
        this.ofertaSeleccionada = ofertaSeleccionada;
    }

    public List<UsuarioHasOferta> getUsuarioHasOfertaFlitradas() {
        return usuarioHasOfertaFlitradas;
    }

    public void setUsuarioHasOfertaFlitradas(List<UsuarioHasOferta> usuarioHasOfertaFlitradas) {
        this.usuarioHasOfertaFlitradas = usuarioHasOfertaFlitradas;
    }

    public UsuarioHasOferta getUsuarioHasOfertaSeleccionada() {
        return usuarioHasOfertaSeleccionada;
    }

    public void setUsuarioHasOfertaSeleccionada(UsuarioHasOferta usuarioHasOfertaSeleccionada) {
        this.usuarioHasOfertaSeleccionada = usuarioHasOfertaSeleccionada;
    }

    public List<Prueba> getPruebasFlitradas() {
        return pruebasFlitradas;
    }

    public void setPruebasFlitradas(List<Prueba> pruebasFlitradas) {
        this.pruebasFlitradas = pruebasFlitradas;
    }

    public Prueba getPreubaSeleccionada() {
        return preubaSeleccionada;
    }

    public void setPreubaSeleccionada(Prueba preubaSeleccionada) {
        this.preubaSeleccionada = preubaSeleccionada;
    }

    public Usuario getUsuariologin() {
        return usuariologin;
    }

    public void setUsuariologin(Usuario usuariologin) {
        this.usuariologin = usuariologin;
    }

    public UsuarioHasOferta getOfertAplicada() {
        return ofertAplicada;
    }

    public void setOfertAplicada(UsuarioHasOferta ofertAplicada) {
        this.ofertAplicada = ofertAplicada;
    }

}
