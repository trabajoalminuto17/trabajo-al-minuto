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
import com.basp.trabajo_al_minuto.service.entity.Oferta;
import com.basp.trabajo_al_minuto.service.entity.Prueba;
import com.basp.trabajo_al_minuto.web.model.ComponenteWeb;
import java.io.IOException;
import java.io.Serializable;
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

    @PostConstruct
    public void init() {
        try {
            if (getOfertaId() != null) {
                ofertaSeleccionada = ofertaEjb.findOferta(getOfertaId());
            }
            if (getUsuarioHasOfertaId() != null) {
                usuarioHasOfertaSeleccionada = usuarioEjb.findUsuarioHasOferta(getUsuarioHasOfertaId());
            }
            if (getPruebaId() != null) {
                preubaSeleccionada = pruebaEjb.findPrueba(getPruebaId());
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
            if (uho.getEstado().getCatalogoId() != 9L) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioHasOfertaId", ((UsuarioHasOferta) event.getObject()).getUsuarioHasOfertaId());
                FacesContext.getCurrentInstance().getExternalContext().redirect(DETALLE_POSTULACION_PAGE);
            } else {
                message = webMessage(CITACION_RECHAZADA);
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

}
